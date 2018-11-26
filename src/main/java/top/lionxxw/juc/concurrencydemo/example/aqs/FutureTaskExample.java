package top.lionxxw.juc.concurrencydemo.example.aqs;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * FutureTask demo

 * created on 2018/5/15 15:00
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("callable do something ...");
                Thread.sleep(3000);
                return "Done";
            }
        });

        ThreadFactory build = new ThreadFactoryBuilder().setNameFormat("md-pool-%d").build();
        ExecutorService singleThread= new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), build, new ThreadPoolExecutor.AbortPolicy());

        singleThread.execute(task);
        singleThread.shutdown();

//        new Thread(task).start();
        log.info("main do something ...");
        Thread.sleep(1000);
        String result = task.get();
        log.info("result:{}",result);
    }
}
