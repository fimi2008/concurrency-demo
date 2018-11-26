package top.lionxxw.juc.concurrencydemo.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

/**
 * 线程安全的

 * created on 2018/4/23 14:20
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {
    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1, 2).put(3, 4).put(5, 6).build();

    /**
     * 使用google包创建不可修改的list,set,map
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("{}", map2.get(3));
        list.add(1);
        set.add(2);
    }
}
