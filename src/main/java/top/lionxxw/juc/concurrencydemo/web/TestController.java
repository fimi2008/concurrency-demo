package top.lionxxw.juc.concurrencydemo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口

 * created on 2018/4/11 17:22
 *
 * @author lionxxw
 * @version 1.0.0
 */
@RestController
@RequestMapping("api")
@Slf4j
public class TestController {

    @RequestMapping("test")
    public String test(){
        return "test";
    }
}
