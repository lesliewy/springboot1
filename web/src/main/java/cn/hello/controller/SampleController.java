package cn.hello.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leslie on 2018/4/25.
 */
@RestController
public class SampleController {

    /**
     * 获取spring application 的参数   java -jar xxx.jar --debug   或者   mvn spring-boot:run --debug
     */
    @Autowired
    private ApplicationArguments applicationArguments;

    @RequestMapping("/home")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/test1")
    String test1() {
        System.out.println(applicationArguments.getOptionNames());
        System.out.println(Arrays.toString(applicationArguments.getSourceArgs()));
        System.out.println(applicationArguments.containsOption("debug"));
        return "this is test7";
    }

}
