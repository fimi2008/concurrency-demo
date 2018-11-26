package top.lionxxw.juc.concurrencydemo.example.publish.singleton;

import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 不足:
 * 1.如果私有构造函数存在过多的处理,会造成加载过慢
 * 2.如果加载后未使用会导致资源的浪费

 * created on 2018/4/17 20:12
 *
 * @author lionxxw
 * @version 1.0.0
 */
@ThreadSafe
public class SingletonExample2 {
    /**
     * 私有的构造函数
     */
    private SingletonExample2(){
    }

    /**
     * 单例对象
     */
    private static SingletonExample2 instance = new SingletonExample2();

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
