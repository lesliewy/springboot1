package cn.hello.interfaces;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by leslie on 2018/4/30.
 */
@Component
public class MyApplicationRunner2 implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationRunner2.class);

    @Override
    @Order(value = 5)
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("this is MyApplicationRunner2");
        Arrays.toString(applicationArguments.getSourceArgs());
    }
}
