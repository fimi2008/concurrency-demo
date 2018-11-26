package top.lionxxw.juc.concurrencydemo.example.deadlock;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁demo
 * 一个简单的死锁类
 * 当DeadLockExample类的对象flag==1时（d1），先锁定o1,睡眠500毫秒
 * 而d1在睡眠的时候另一个flag==0的对象（d2）线程启动，先锁定o2,睡眠500毫秒
 * d1睡眠结束后需要锁定o2才能继续执行，而此时o2已被td2锁定；
 * d2睡眠结束后需要锁定o1才能继续执行，而此时o1已被td1锁定；
 * d1、d2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。

 * created on 2018/5/15 23:40
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class DeadLockExample implements Runnable {
    public int flag;
    // 静态对象是类的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("print o1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("print o2");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockExample d1 = new DeadLockExample();
        d1.flag = 1;
        DeadLockExample d2 = new DeadLockExample();
        d2.flag = 0;
        //d1,d2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。
        //d2的run()可能在d1的run()之前运行
        new Thread(d1).start();
        new Thread(d2).start();
    }
}
