package top.lionxxw.juc.concurrencydemo.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized 认识

 * created on 2018/4/12 20:37
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class SyncExample1 {

    /**
     * synchronized修饰代码块,作用于当前对象
     * @param j
     */
    private void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}", j, i);
            }
        }
    }

    /**
     * synchronized修饰方法,作用于当前对象
     * @param j
     */
    private synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SyncExample1 example1 = new SyncExample1();
        SyncExample1 example2 = new SyncExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
    }
}
