package top.lionxxw.juc.concurrencydemo.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Future 事例

 * created on 2018/5/15 14:50
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class FutureExample1 {

    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            log.info("callable do something...");
            Thread.sleep(3000);
            return "Done";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("main do something...");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result:{}", result);
    }
}
