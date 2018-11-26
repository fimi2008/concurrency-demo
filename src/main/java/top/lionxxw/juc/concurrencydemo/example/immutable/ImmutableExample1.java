package top.lionxxw.juc.concurrencydemo.example.immutable;

import lombok.extern.slf4j.Slf4j;
import com.google.common.collect.Maps;
import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;

import java.util.Map;

/**
 * 非线程安全的,虽然map用final关键字修饰,但是还是可以改变其中的内容

 * created on 2018/4/23 14:20
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();
    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        map.put(1,1);
        log.info("{}", map.get(1));
    }

    private void test(final int a){
//        a = 1;
    }
}
