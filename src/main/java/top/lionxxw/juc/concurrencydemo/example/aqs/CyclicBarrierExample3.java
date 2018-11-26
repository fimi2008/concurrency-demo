package top.lionxxw.juc.concurrencydemo.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**

 * created on 2018/5/8 15:19
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class CyclicBarrierExample3 {

    // 指定等待n个线程执行
    private static CyclicBarrier barrier = new CyclicBarrier(5,()->{
        log.info("callback is running...");
    });

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        barrier.await();
        log.info("{} continue", threadNum);
    }
}
