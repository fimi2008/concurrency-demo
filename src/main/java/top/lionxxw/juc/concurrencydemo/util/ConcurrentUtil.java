package top.lionxxw.juc.concurrencydemo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 并发测试工具类

 * created on 2018/4/12 17:34
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public abstract class ConcurrentUtil {
    /**
     * 开始测试
     * @param clientTotal 请求总数
     * @param threadTotal 同时并发执行的线程数
     * @throws InterruptedException
     */
    public void start(int clientTotal, int threadTotal) throws InterruptedException {
        ExecutorService service =  Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            service.execute(()->{
                try {
                    semaphore.acquire();
                    function();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        log.info("执行完毕");
    }

    public abstract void function();

    public static void main(String[] args) throws InterruptedException {
        ConcurrentUtil util = new ConcurrentUtil() {
            private int count;

            @Override
            public void function() {
                count++;
                log.info("count:{}",count);
            }
        };
        util.start(5000,200);
    }
}
