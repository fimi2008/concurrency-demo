package top.lionxxw.juc.concurrencydemo.example.lock;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.ThreadSafe;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 悲观读写

 * created on 2018/4/12 17:21
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@ThreadSafe
public class LockExample2 {

    private final Map<String, Data> map = new TreeMap<>();
    // 使用场景并不多
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Set<String> getAllKeys(){
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data get(String key){
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data data){
        /**
         * 悲观读写
         * 在写的时候是不允许读锁还保持着
         * @return
         */
        writeLock.lock();
        try {
            return map.put(key,data);
        } finally {
            writeLock.unlock();
        }
    }

    class Data{

    }
}
