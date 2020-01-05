package cn.hello.interfaces;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by leslie on 2018/4/30.
 */
@Component
public class MyCommandLineRunner2 implements CommandLineRunner {

    @Order(value = 30)
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("this is MyCommandLineRunner2");
    }

}
