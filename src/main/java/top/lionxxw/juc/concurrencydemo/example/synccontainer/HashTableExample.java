package top.lionxxw.juc.concurrencydemo.example.synccontainer;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Hashtable 是线程安全的类

 * created on 2018/4/24 10:45
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@ThreadSafe
public class HashTableExample {
    /**
     * 请求总数
     */
    private static int clientTotal = 5000;
    /**
     * 同时并发执行的线程数
     */
    private static int threadTotal = 200;

    private static Map<Integer,Integer> map = new Hashtable<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            service.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        log.info("size:{}", map.size());
    }

    private static void update(int i){
        map.put(i,i);
    }
}
