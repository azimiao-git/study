package top.zl.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zl
 * 2021/08/16
 */
public class HeroInvocation implements InvocationHandler {

    private final Object target;

    public HeroInvocation(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("找人 带去商店");
        Object invoke = method.invoke(target, args);
        System.out.println("找人 把装备待会家");
        return invoke;
    }
}
