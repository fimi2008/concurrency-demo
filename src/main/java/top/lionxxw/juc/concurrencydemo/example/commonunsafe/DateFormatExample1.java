package top.lionxxw.juc.concurrencydemo.example.commonunsafe;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SimpleDateFormat 非线程安全

 * created on 2018/4/24 10:45
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@NotThreadSafe
public class DateFormatExample1 {
    /**
     * 请求总数
     */
    private static int clientTotal = 5000;
    /**
     * 同时并发执行的线程数
     */
    private static int threadTotal = 200;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

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
    }

    private static void update(){
        try {
            format.parse("20140601");
        } catch (ParseException e) {
            log.error("parse exception",e);
        }
    }
}
