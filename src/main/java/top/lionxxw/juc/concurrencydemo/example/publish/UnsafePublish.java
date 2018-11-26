package top.lionxxw.juc.concurrencydemo.example.publish;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;

import java.util.Arrays;

/**
 * 对象的发布

 * created on 2018/4/17 19:58
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return this.states;
    }

    /**
     * 如何安全发布对象
     * 1.在静态初始化函数中初始化一个对象引用
     * 2.将对象的引用保存到volatile类型域或者AtomicReference对象中
     * 3.将对象的引用保存在某个正确构造对象的final类型域中
     * 4.将对象的应用保存到一个由锁保护的域中
     * @param args
     */
    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
