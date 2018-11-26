package top.lionxxw.juc.concurrencydemo.example.synccontainer;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 同步的容器也有线程不安全的时候

 * created on 2018/4/25 14:26
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {
    private static Vector<Integer> list = new Vector<>();

    public static void main(String[] args) {
        while (true){
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        list.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }

}
