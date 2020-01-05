package cn.hello;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.hello.web.converters.JavaSerializationConverter;
import cn.hello.web.converters.MyMessageConverter;

/**
 * <pre>
 *     spring mvc 的功能通常都需要配置该类.
 *     spring5.0 中implements WebMvcConfigurer 即可, 之前是 extends WebMvcConfigurerAdapter(deprecated): 原因是java1.8 中
 *     的interface 可以带有默认实现, 不需要WebMvcConfigurerAdapter了.
 * </pre>
 * 
 * Created by leslie on 2018/5/1.
 */
@Configuration
// TODO 这个注解暂时不能加，不然 无法访问静态资源, 不知道为什么
// @EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 直接将url 指向某个页面.
        registry.addViewController("/page1").setViewName("page1.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        // begin: 用于spring-security
        registry.addViewController("/home").setViewName("security/home");
        registry.addViewController("/").setViewName("security/home");
        registry.addViewController("/hello").setViewName("security/hello");
        registry.addViewController("/login").setViewName("security/login");
        // end

        // begin: 用于spring-security 中的ip login demo
        registry.addViewController("/ip").setViewName("security/ipHello");
        registry.addViewController("/ipLogin").setViewName("security/ipLogin");
        // end.
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);// 当此参数设置为true的时候，那么/user.html，/user.aa，/user.*都能是正常访问的。
    }

    /**
     * <pre>
     *   添加converter的第一种方式，代码很简单，也是推荐的方式
     *   HttpMessageConverter 类型的bean 都会被添加到 myMessageConverter 列表中;
     *   这样做springboot会把我们自定义的converter放在顺序上的最高优先级（List的头部）,
     *   即有多个converter都满足Accpet/ContentType/MediaType的规则时，优先使用我们这个
     * </pre>
     * 
     * @return
     */
    @Bean
    public JavaSerializationConverter javaSerializationConverter() {
        return new JavaSerializationConverter();
    }

    /**
     * <pre>
     *   添加converter的第二种方式  javaSerializationConverter 没必要是bean
     *   通常在只有一个自定义WebMvcConfigurerAdapter时，会把这个方法里面添加的converter(s)依次放在最高优先级（List的头部）
     *   虽然第一种方式的代码先执行，但是bean的添加比这种方式晚，所以方式二的优先级 大于 方式一
     * </pre>
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // add方法可以指定顺序，有多个自定义的WebMvcConfigurerAdapter时，可以改变相互之间的顺序
        // 但是都在springmvc内置的converter前面
        converters.add(new JavaSerializationConverter());
        converters.add(myMessageConverter());
    }

    /**
     * <pre>
     *   添加converter的第三种方式, javaSerializationConverter 没必要是bean
     *   同一个WebMvcConfigurerAdapter中的configureMessageConverters方法先于extendMessageConverters方法执行
     *   可以理解为是三种方式中最后执行的一种，不过这里可以通过add指定顺序来调整优先级，也可以使用remove/clear来删除converter，功能强大
     *   使用converters.add(xxx)会放在最低优先级（List的尾部）
     *   使用converters.add(0,xxx)会放在最高优先级（List的头部）
     * </pre>
     * 
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, javaSerializationConverter());
        converters.add(myMessageConverter());
    }

    // @Bean
    public MyMessageConverter myMessageConverter() {
        return new MyMessageConverter();
    }
}
