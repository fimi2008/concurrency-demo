package top.lionxxw.juc.concurrencydemo.example.publish.singleton;

import top.lionxxw.juc.concurrencydemo.annoations.Recommend;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

/**
 * 枚举模式: 最安全

 * created on 2018/4/17 20:12
 *
 * @author lionxxw
 * @version 1.0.0
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {
    /**
     * 私有的构造函数
     */
    private SingletonExample7() {
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;

        /**
         * JVM保证这个方法绝对只调用一次
         *
         * @return
         */
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }

    /**
     * 静态的工厂方法
     *
     * @return
     */
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
