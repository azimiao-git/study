package top.zl.v1;


import top.zl.annotation.*;
import top.zl.convert.Converter;
import top.zl.handler.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;

/**
 * @author zl
 * 2021/08/23
 */
@WebServlet(urlPatterns = "/",initParams = {@WebInitParam(name = "contextConfig", value = "application.properties")},loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    /**
     * 配置文件的内容
     */
    private final Properties config = new Properties();

    /**
     * 扫描到的所有类
     */
    private final List<String> classNames = new ArrayList<>();

    /**
     * 容器
     */
    private final Map<String, Object> ioc = new HashMap<>();

    /**
     * 映射关系
     */
    private final List<HandlerMapping> handlerMappings = new ArrayList<>();

    /**
     * 参数转换
     */
    private final List< Converter<?,?>> converters = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1.加载配置文件
        loadConfig(config.getInitParameter("contextConfig"));
        //2.扫描相关的类
        scanner(this.config.getProperty("scanPackage"));
        //3.初始化类并放入IOC容器中
        initInstance();
        //4.依赖注入
        autowired();
        //5.初始化映射关系
        initHandlerMapping();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据请求路径获取对应的Handler
        String servletPath = req.getServletPath();
        HandlerMapping handler = getHandler(servletPath);

        if (handler==null){
            resp.getWriter().write("404 NOT FOUND!");
            return;
        }

        Method method = handler.getMethod();
        Object[] values = new Object[method.getParameters().length];
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {

            if (parameters[i].getType()==HttpServletRequest.class){
                values[i] = req;
            }else if (parameters[i].getType()==HttpServletResponse.class){
                values[i] = resp;
            }else{
                ServletInputStream inputStream = req.getInputStream();
            }

        }

    }

    private HandlerMapping getHandler(String servletPath) {
        for (HandlerMapping mapping : handlerMappings) {
            if (mapping.getUri().equals(servletPath)){
                return mapping;
            }
        }
        return null;
    }


    /**
     * 初始化url和方法的对应关系
     */
    private void initHandlerMapping() {
        for (Object value : ioc.values()) {
            Class<?> clazz = value.getClass();
            if (!clazz.isAnnotationPresent(Controller.class)) continue;

            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
                baseUrl = annotation.value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) continue;

                RequestMapping annotation = method.getAnnotation(RequestMapping.class);

                String url = ("/" + baseUrl + "/" + annotation.value()).replaceAll("/+","/");
                handlerMappings.add(new HandlerMapping(value,method,url));
            }

        }
    }

    private void loadConfig(String contextConfig) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfig);
        try {
            config.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void scanner(String scanPackage) {
        //top.zl
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        assert url != null;
        File classpath = new File(url.getFile());
        for (File file : Objects.requireNonNull(classpath.listFiles())) {
            if (file.isDirectory()) {
                scanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                //把全类名存下来
                System.out.println((scanPackage + "." + file.getName().replace(".class", "")));
                classNames.add(scanPackage + "." + file.getName().replace(".class", ""));
            }
        }
    }

    private void initInstance() {
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                //接口不实例
                if (clazz.isInterface()) continue;
                //有注解才实例化
                if (!clazz.isAnnotationPresent(Component.class) && !clazz.isAnnotationPresent(Controller.class)
                        && !clazz.isAnnotationPresent(Service.class)) continue;


                Object o = clazz.newInstance();
                String name = this.instanceName(clazz);
                if (ioc.containsKey(name)) {
                    throw new RuntimeException("The " + name + " is exist");
                }
                ioc.put(name, o);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回实例的名称
     */
    private String instanceName(Class<?> clazz) {
        Component component = clazz.getAnnotation(Component.class);
        if (component != null && !"".equals(component.value())) {
            return component.value();
        }
        Controller controller = clazz.getAnnotation(Controller.class);
        if (controller != null && !"".equals(controller.value())) {
            return controller.value();
        }
        Service service = clazz.getAnnotation(Service.class);
        if (service != null && !"".equals(service.value())) {
            return service.value();
        }
        //默认使用类名首字母小写
        String name = clazz.getSimpleName();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    private void autowired() {
        for (Object bean : ioc.values()) {
            Field[] declaredFields = bean.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                AutoWired annotation = field.getAnnotation(AutoWired.class);
                if (annotation != null) {
                    try {
                        field.setAccessible(true);
                        Object value = null;
                        //按照类型注入
                        for (Object o : ioc.values()) {
                            if (field.getType().isInstance(o)) {
                                value = o;
                                break;
                            }
                        }
                        field.set(bean, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
