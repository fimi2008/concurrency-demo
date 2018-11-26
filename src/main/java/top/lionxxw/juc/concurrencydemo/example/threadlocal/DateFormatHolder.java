package top.lionxxw.juc.concurrencydemo.example.threadlocal;

import java.text.SimpleDateFormat;

/**
 * ThreadLocal

 * created on 2018/4/23 16:03
 *
 * @author lionxxw
 * @version 1.0.0
 */
public class DateFormatHolder {
    private final static ThreadLocal<SimpleDateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<>();

    public static void add(SimpleDateFormat format){
        DATE_FORMAT_THREAD_LOCAL.set(format);
    }

    public static SimpleDateFormat get(){
        return DATE_FORMAT_THREAD_LOCAL.get();
    }

    public static void remove(){
        DATE_FORMAT_THREAD_LOCAL.remove();
    }
}
