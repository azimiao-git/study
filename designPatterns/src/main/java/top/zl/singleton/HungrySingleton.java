package top.zl.singleton;

/**
 * 饿汉式单例模式
 * @author zl
 * 2021/08/09
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return instance;
    }
}
