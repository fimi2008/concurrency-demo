package top.lionxxw.juc.concurrencydemo.example.publish.singleton;

import top.lionxxw.juc.concurrencydemo.annoations.NotRecommend;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建

 * created on 2018/4/17 20:12
 *
 * @author lionxxw
 * @version 1.0.0
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    /**
     * 私有的构造函数
     */
    private SingletonExample3(){
    }

    /**
     * 单例对象
     */
    private static SingletonExample3 instance = null;

    /**
     * 静态的工厂方法
     * 添加synchronized关键字变成线程安全的,但是不推荐
     * @return
     */
    public static synchronized SingletonExample3 getInstance(){
        if (null == instance){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
