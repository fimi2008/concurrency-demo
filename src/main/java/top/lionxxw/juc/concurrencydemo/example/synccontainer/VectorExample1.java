package top.lionxxw.juc.concurrencydemo.example.synccontainer;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Vector 是线程安全的

 * created on 2018/4/25 14:26
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@ThreadSafe
public class VectorExample1 {
    /**
     * 请求总数
     */
    private static int clientTotal = 5000;
    /**
     * 同时并发执行的线程数
     */
    private static int threadTotal = 200;

    private static Vector<Integer> list = new Vector<>();

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
        list.add(1);
    }
}
