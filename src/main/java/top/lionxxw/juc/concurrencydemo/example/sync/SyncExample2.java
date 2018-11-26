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
public class SyncExample2 {

    /**
     * synchronized修饰一个类,作用于这个类的所有对象
     *
     * @param j
     */
    private static void test1(int j) {
        synchronized (SyncExample2.class){
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}", j, i);
            }
        }
    }

    /**
     * synchronized修饰一个静态方法,作用于这个类的所有对象
     *
     * @param j
     */
    private static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            SyncExample2.test1(1);
        });
        executorService.execute(() -> {
            SyncExample2.test1(2);
        });
    }
}
