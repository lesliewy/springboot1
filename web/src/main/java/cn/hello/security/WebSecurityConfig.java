package cn.hello.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.hello.security.authentication.iplogin.IpAuthenticationProcessingFilter;
import cn.hello.security.authentication.iplogin.IpAuthenticationProvider;

/**
 * <pre>
 *     http://blog.didispace.com/xjf-spring-security-1/
 *
 *     spring security 重点配置.
 *    EnableWebSecurity: 引入两个核心配置: WebSecurityConfiguration(安全配置), AuthenticationConfiguration(身份认证)
 *    WebSecurityConfiguration: 引入filter chain.
 *       springSecurityFilterChain: springboot 启动日志中可以看到所有filter. DefaultSecurityFilterChain: Creating filter chain....
 *          SecurityContextPersistenceFilter: 两个主要职责：请求来临时，创建SecurityContext安全上下文信息，请求结束时清空SecurityContextHolder. securitycontext 存于session中.
 *          UsernamePasswordAuthenticationFilter: 表单username, password认证.
 *          AnonymousAuthenticationFilter: 匿名身份认证.
 *          ExceptionTranslationFilter: 异常转换, 针对不同异常区别处理.
 *
 *    AuthenticationConfiguration
 *       AuthenticationManager
 *
 *    WebSecurityConfigurerAdapter: adapter 模式, adapter implements 客户接口, 内部引用真实的对象, 真实对象接口 -> 客户需要的接口.
 * </pre>
 * 
 * Created by leslie on 2020/1/2.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * <pre>
     *     配置哪些url应该被安全保护.
     * </pre>
     * 
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * <pre>
         *     除 "/", "/home", "/login", "/logout" 外的url都需要认证, 未认证访问时会跳转至"/login"
         *     logout() 默认URL "/logout", 点击后权限被回收, 重定向到 login?logout
         * </pre>
         */
//        http.authorizeRequests().antMatchers("/",
//                                             "/home").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();

        /**
         * 用于iplogin demo.
         *     1, 添加 IpAuthenticationToken;
         *     2, 添加 IpAuthenticationProcessingFilter;
         *     3, 添加 IpAuthenticationProvider
         *     4, 配置WebSecurityConfigAdapter
         *     5, 配置SpringMVC.
         */
        // 禁用websecurity
        /*
        http.authorizeRequests().antMatchers("/",
                                             "/home").permitAll().antMatchers("/ipLogin").permitAll().anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().exceptionHandling().accessDeniedPage("/ipLogin").authenticationEntryPoint(loginUrlAuthenticationEntryPoint());

        // 添加自定义filter. 注意放置的顺序 这很关键
        http.addFilterBefore(ipAuthenticationProcessingFilter(authenticationManager()),
                             UsernamePasswordAuthenticationFilter.class);
                             */

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 禁用websecurity
        /*
        auth.authenticationProvider(ipAuthenticationProvider());
        */
    }

    /**
     * <pre>
     *     内存中用户认证器.
     * </pre>
     * 
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * <pre>
     * ip认证者配置
     *
     * </pre>
     */

    @Bean
    public IpAuthenticationProvider ipAuthenticationProvider() {
        return new IpAuthenticationProvider();
    }

    // 配置封装ipAuthenticationToken的过滤器
    public IpAuthenticationProcessingFilter ipAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        IpAuthenticationProcessingFilter ipAuthenticationProcessingFilter = new IpAuthenticationProcessingFilter();
        // 为过滤器添加认证器
        ipAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        // 重写认证失败时的跳转页面
        ipAuthenticationProcessingFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/ipLogin?error"));
        return ipAuthenticationProcessingFilter;
    }

    // 配置登录端点
    @Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/ipLogin");
        return loginUrlAuthenticationEntryPoint;
    }
}
