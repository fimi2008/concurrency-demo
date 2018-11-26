package top.lionxxw.juc.concurrencydemo.example.lock;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 的使用

 * created on 2018/4/12 17:21
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@ThreadSafe
public class LockExample5 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock(); // 获取锁，并加入AQS队列
                log.info("wait signal"); // 1
                condition.await(); // 线程1将释放锁，并且将自己沉睡，等待唤醒（从AQS同步队列中移除，加入到Condition等待队列）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4 线程2释放锁，此时AQS队列中只剩下线程1，线程1开始执行
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock(); // 由于线程1释放锁被唤醒,线程2获取锁，加入AQS同步队列中
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll(); // 发送信号，这个时候Condition的等待队列中只有线程1一个节点，于是它被取出来，并被加入到AQS的等待队列中。  注意，这个时候，线程1 并没有被唤醒。
            log.info("send signal ~ "); // 3
            reentrantLock.unlock(); // 线程2释放锁，线程2结束
        }).start();
    }
}
