package top.lionxxw.juc.concurrencydemo.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

/**
 * 线程安全的

 * created on 2018/4/23 14:20
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private static Map<Integer, Integer> map = Maps.newHashMap();
    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        // unmodifiableMap 返回一个不可修改的map
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,1);
        log.info("{}", map.get(1));
    }
}
