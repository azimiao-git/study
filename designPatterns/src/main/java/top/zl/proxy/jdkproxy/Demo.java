package top.zl.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author zl
 * 2021/08/16
 */
public class Demo {
    public static void main(String[] args) {
        //创建目标类
        Hero hero = new Hero("菜鸡");
        //生成代理类
        Equip proxy = (Equip) Proxy.newProxyInstance(Hero.class.getClassLoader(), Hero.class.getInterfaces(), new HeroInvocation(hero));
        proxy.buy();
    }
}
