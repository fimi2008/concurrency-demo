package top.lionxxw.juc.concurrencydemo.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin框架 计算值

 * created on 2018/5/15 15:13
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class ForkJoinExample extends RecursiveTask<Integer> {
    private final static int THREAD_HOLD = 100; // 临界值
    private int start;
    private int end;

    public ForkJoinExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean flag = (end - start) <= THREAD_HOLD;
        if (flag) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinExample f1 = new ForkJoinExample(start, middle);
            ForkJoinExample f2 = new ForkJoinExample(middle + 1, end);
            f1.fork();
            f2.fork();
            Integer s1 = f1.join();
            Integer s2 = f2.join();
            sum = s1 + s2;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinExample task = new ForkJoinExample(1, 1000000);
        Future<Integer> result = pool.submit(task);
        try {
            log.info("result:{}", result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
