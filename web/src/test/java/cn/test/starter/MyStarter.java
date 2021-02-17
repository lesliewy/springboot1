package cn.test.starter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.wy.mystarter.service.HelloService;

/**
 * Created by leslie on 2020/10/28.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyStarter {

    @Autowired
    private HelloService helloService;

    @Test
    public void contextLoads() {
        System.out.println("aaa");
        System.out.println(helloService.sayHello());
    }

    @Test
    public void test1() {
        System.out.println("bbb");
    }
}
