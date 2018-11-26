package top.lionxxw.juc.concurrencydemo.example.concurrent;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

import java.util.List;
import java.util.concurrent.*;

/**
 * CopyOnWriteArrayList 是线程安全的类
 * 读写分离，读不加锁，写加锁
 * 适用于读多写少的场景

 * created on 2018/4/24 10:45
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {
    /**
     * 请求总数
     */
    private static int clientTotal = 5000;
    /**
     * 同时并发执行的线程数
     */
    private static int threadTotal = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            service.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        log.info("size:{}", list.size());
    }

    private static void update(){
        /**
         * 1.先获得锁lock
         * 2.获取原数组
         * 3.copy赋值原数组为一个新的数组并且数组长度+1
         * 4.将新的元素赋值到新的数组中
         * 5.新数组替换原数组
         * 6.释放锁unlock
         */
        list.add(1);
    }
}
