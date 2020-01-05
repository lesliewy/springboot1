package cn.hello.interfaces;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by leslie on 2018/4/30.
 */
@Component
public class MyApplicationRunner1 implements ApplicationRunner {

    @Order(value = 20)
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("this is MyApplicationRunner1");
        Arrays.toString(applicationArguments.getSourceArgs());
    }

}
