package top.lionxxw.juc.concurrencydemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lionxxw.juc.concurrencydemo.example.threadlocal.RequestHolder;

/**
 * threadLocal测试接口

 * created on 2018/4/23 17:08
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Controller
@RequestMapping("threadLocal")
public class ThreadLocalController {

    @RequestMapping("test")
    @ResponseBody
    public Long test(){
        return RequestHolder.get();
    }
}
