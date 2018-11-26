package top.lionxxw.juc.concurrencydemo.example.publish.singleton;

import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建

 * created on 2018/4/17 20:12
 *
 * @author lionxxw
 * @version 1.0.0
 */
@NotThreadSafe
public class SingletonExample4 {
    /**
     * 私有的构造函数
     */
    private SingletonExample4() {
    }

    /**
     * 单例对象
     */
    private static SingletonExample4 instance = null;

    /**
     * 静态的工厂方法
     * cpu处理步骤:
     * 1.分配对象内存空间 memory=allocate()
     * 2.初始化对象 ctorInstance()
     * 3.设置instance指向刚分配的内存 instance = memory
     *
     * 多线程下问题:jvm和cpu优化会发生指令重排,以上操作可能变成1,3,2
     *
     *
     * @return
     */
    public static SingletonExample4 getInstance() {
        if (null == instance) { // B线程,可能只执行到1步骤,但是此时instance有内存地址,但是未进行初始化对象
            /**
             * 双重检测机制,添加同步锁
             */
            synchronized (SingletonExample4.class) {
                if (null == instance) {
                    instance = new SingletonExample4();  // A线程  可能只需到3步骤
                }
            }
        }
        return instance;
    }
}
