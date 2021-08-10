package top.zl.singleton;

/**
 * 懒汉式单例模式
 *
 * @author zl
 * 2021/08/09
 */
public class LazySimpleSingleton {

    private static LazySimpleSingleton instance;

    private LazySimpleSingleton() {
    }

    /**
     * 线程不安全
     */
    public static LazySimpleSingleton getInstance() {
        if (instance == null) {
            instance = new LazySimpleSingleton();
        }
        return instance;
    }

    /**
     * 线程安全，但是效率低，每次都需要排队
     */
    public static LazySimpleSingleton instance2() {
        synchronized (LazySimpleSingleton.class) {
            if (instance ==null){
                instance = new LazySimpleSingleton();
            }
        }
        return instance;
    }

    /**
     * 双重检测锁，线程安全，效率高一点
     */
    public static LazySimpleSingleton instance() {
        if (instance == null) {
            synchronized (LazySimpleSingleton.class) {
                if (instance ==null){
                    instance = new LazySimpleSingleton();
                }
            }
        }
        return instance;
    }

}
