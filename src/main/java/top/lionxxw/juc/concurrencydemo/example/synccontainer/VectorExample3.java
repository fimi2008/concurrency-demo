package top.lionxxw.juc.concurrencydemo.example.synccontainer;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * 同步的容器也有线程不安全的时候

 * created on 2018/4/25 14:26
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
public class VectorExample3 {

    // ConcurrentModificationException
    private static void test1(Vector<Integer> list) {
        for (Integer i : list) {
            if (i.equals(3)) {
                list.remove(i);
            }
        }
    }

    // success
    private static void test2(Vector<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(3)) {
                // iterator.remove(); // success
                // ConcurrentModificationException
                list.remove(next);
            }
        }
    }

    // success
    private static void test3(Vector<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(3)) {
                list.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> list = new Vector<>();
        list.add(1);
        list.add(2);
        list.add(3);
        test2(list);
    }

}
