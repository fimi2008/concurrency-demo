package top.lionxxw.juc.concurrencydemo.example.publish.singleton;

import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建

 * created on 2018/4/17 20:12
 *
 * @author lionxxw
 * @version 1.0.0
 */
@NotThreadSafe
public class SingletonExample1 {
    /**
     * 私有的构造函数
     */
    private SingletonExample1(){
    }

    /**
     * 单例对象
     */
    private static SingletonExample1 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample1 getInstance(){
        /**
         * 此段,在多线程下存在风险
         */
        if (null == instance){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
