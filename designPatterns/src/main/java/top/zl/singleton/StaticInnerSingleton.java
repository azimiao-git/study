package top.zl.singleton;

/**
 * 静态内部类
 *
 * @author zl
 * 2021/08/10
 */
public class StaticInnerSingleton {

    private StaticInnerSingleton(){}

    static class HandlerInstance{
        private static final  StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance(){
        return HandlerInstance.instance;
    }
}
