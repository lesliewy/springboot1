package cn.wy.mystarter.autoconfigure;

import cn.wy.mystarter.properties.HelloProperties;
import cn.wy.mystarter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by leslie on 2020/10/27.
 */

@Configuration
// 启动 @ConfigurationProperties
@EnableConfigurationProperties(HelloProperties.class)
// classpath 中有 HelloService 时生效.
@ConditionalOnClass(HelloService.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    HelloProperties helloProperties;

    @Bean
    HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setName(helloProperties.getName());
        helloService.setMsg(helloProperties.getMsg());
        return helloService;
    }
}
