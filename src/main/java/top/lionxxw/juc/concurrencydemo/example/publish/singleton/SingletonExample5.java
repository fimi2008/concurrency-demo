package top.lionxxw.juc.concurrencydemo.example.publish.singleton;

import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

/**
 * 懒汉模式 -> volatile关键字+双重同步锁 单例模式
 * 单例实例在第一次使用时进行创建

 * created on 2018/4/17 20:12
 *
 * @author lionxxw
 * @version 1.0.0
 */
@ThreadSafe
public class SingletonExample5 {
    /**
     * 私有的构造函数
     */
    private SingletonExample5() {
    }

    /**
     * 单例对象
     * 通过添加volatile关键字限制指令重排(volatile 此例中的使用场景-->双重检测机制)
     * 知识点:volatile+双重检测机制-->禁止指令重排
     */
    private volatile static SingletonExample5 instance = null;

    /**
     * 静态的工厂方法
     * cpu处理步骤:
     * 1.分配对象内存空间 memory=allocate()
     * 2.初始化对象 ctorInstance()
     * 3.设置instance指向刚分配的内存 instance = memory
     *
     * @return
     */
    public static SingletonExample5 getInstance() {
        if (null == instance) {
            /**
             * 双重检测机制,添加同步锁
             */
            synchronized (SingletonExample5.class) {
                if (null == instance) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
