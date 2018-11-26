package top.lionxxw.juc.concurrencydemo.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 代码并发测试

 * created on 2018/4/12 17:21
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicIntegerFieldUpdater<AtomicExample4> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample4.class, "count");
    @Getter
    public volatile int count = 100;

    private static AtomicExample4 example4 = new AtomicExample4();

    public static void main(String[] args) {
        if (updater.compareAndSet(example4, 100, 120)){
            log.info("update success 1,{}",example4.getCount());
        }
        if (updater.compareAndSet(example4, 100, 120)){
            log.info("update success 2,{}",example4.getCount());
        }else {
            log.info("update failhhh,{}",example4.getCount());
        }
    }
}
