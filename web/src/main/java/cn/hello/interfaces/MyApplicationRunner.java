package cn.hello.interfaces;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SpringApplication 即将完成时暴露给用户的接口;
 * ApplicationRunner: 参数是ApplicationArguments
 * CommandLineRunner：参数是String...
 *
 * 当然，必须要是bean( @Component @Service 等)
 *
 * Created by leslie on 2018/4/30.
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Order(value = 15)
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("this is MyApplicationRunner");
        Arrays.toString(applicationArguments.getSourceArgs());
    }
}
