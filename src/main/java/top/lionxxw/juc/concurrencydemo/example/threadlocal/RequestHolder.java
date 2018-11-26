package top.lionxxw.juc.concurrencydemo.example.threadlocal;

/**
 * ThreadLocal

 * created on 2018/4/23 16:03
 *
 * @author lionxxw
 * @version 1.0.0
 */
public class RequestHolder {
    private final static ThreadLocal<Long> REQUEST_HOLDER = new ThreadLocal<>();

    public static void add(Long id){
        REQUEST_HOLDER.set(id);
    }

    public static Long get(){
        return REQUEST_HOLDER.get();
    }

    public static void remove(){
        REQUEST_HOLDER.remove();
    }
}
