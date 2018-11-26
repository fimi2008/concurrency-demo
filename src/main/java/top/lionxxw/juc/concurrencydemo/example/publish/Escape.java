package top.lionxxw.juc.concurrencydemo.example.publish;

import lombok.extern.slf4j.Slf4j;
import top.lionxxw.juc.concurrencydemo.annoations.NotRecommend;
import top.lionxxw.juc.concurrencydemo.annoations.NotThreadSafe;

/**
 * 对象的逸出

 * created on 2018/4/17 20:03
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
