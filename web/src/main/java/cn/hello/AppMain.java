package cn.hello;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.hello.listeners.springboot.ApplicationListenerEnvironmentPrepared;
import cn.hello.listeners.springboot.ApplicationListenerFailed;
import cn.hello.listeners.springboot.ApplicationListenerPrepared;
import cn.hello.listeners.springboot.ApplicationListenerStarted;

/**
 * Created by leslie on 2018/4/30.
 */
// @SpringBootApplication(等价于以下3个. 注意ComponentScan.class 中的basePackages属性)
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class AppMain {

    public static void main(String[] args) throws Exception {
        /**
         * 配置 SpringApplication, static 方式
         */
        /*
         * SpringApplication.run(AppMain.class, args);
         */

        /**
         * 普通方式, 也可以使用 application.properties
         */
        SpringApplication springApplication = new SpringApplication(AppMain.class);
        springApplication.setBannerMode(Banner.Mode.OFF);

        /**
         * fluent API 方式
         */
        /*
         * new SpringApplicationBuilder().sources(AppMain.class).bannerMode(Banner.Mode.OFF).run(args);
         */

        /**
         * <pre>
         * 添加springboot 的事件监听;
         * ApplicationStartingEvent:
         * ApplicationEnvironmentPreparedEvent:
         * ApplicationPreparedEvent:
         * ApplicationReadyEvent:
         * ApplicationFailedEvent:
         * 通常情况下没必要使用springboot的事件监听, 业务上使用spring framework 的; springboot 的通常是框架内部使用;
         * </pre>
         */
        springApplication.addListeners(new ApplicationListenerEnvironmentPrepared());
        springApplication.addListeners(new ApplicationListenerFailed());
        springApplication.addListeners(new ApplicationListenerPrepared());
        springApplication.addListeners(new ApplicationListenerStarted());

        // 是否允许command line 的参数, 例如: --server.port=9000
        // springApplication.setAddCommandLineProperties(false);

        springApplication.run(args);
    }
}
