package top.lionxxw.juc.concurrencydemo.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池demo

 * created on 2018/5/15 22:01
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        log.info("start...");
        /**
         * 延迟3秒执行
         */
        executorService.schedule(() -> log.info("schedule do something"), 3, TimeUnit.SECONDS);
        /**
         * 定时执行
         * initialDelay： 首次延迟时间
         * period：每次相隔时间
         */
        executorService.scheduleAtFixedRate(()->log.info("rate run ..."),1,3, TimeUnit.SECONDS);

        /**
         * 定时器
         *
         */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run ...");
            }
        }, new Date(), 5 * 1000);// date 首次执行时间， period：每次执行相隔时间
//        executorService.shutdown();
    }
}
