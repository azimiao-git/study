package top.zl.v1;


import top.zl.annotation.Component;
import top.zl.annotation.Controller;
import top.zl.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private final Map<String,Object> ioc = new HashMap<>();

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
    }

    private void loadConfig(String contextConfig) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfig);
        try {
            config.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (resourceAsStream!=null){
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
        for (File file : classpath.listFiles()) {
            if (file.isDirectory()){
                scanner(scanPackage+"."+file.getName());
            }else{
                if(!file.getName().endsWith(".class")){continue;}
                //把全类名存下来
                System.out.println((scanPackage + "." + file.getName().replace(".class", "")));
                classNames.add(scanPackage+"."+file.getName().replace(".class",""));
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
                ioc.put(this.instanceName(clazz), o);
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

    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

}