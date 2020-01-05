package cn.hello.properties;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

/**
 * <pre>
 *   外部配置: 外部配置文件加载优先级 references 24
 *   RandomValuePropertySource: 实现随机值;
 * </pre>
 */
@RestController
@Data
@ConfigurationProperties(prefix = "wy")
public class Properties1 {

    @Value("${leslie.name}")
    private String       name;

    @Value("${leslie.full.name}")
    private String       fullName;

    @Value("${my.secret}")
    private String       secret;

    @Value("${my.number}")
    private int          number;

    @Value("${my.bignumber}")
    private long         bignumber;

    @Value("${my.uuid}")
    private String       uuid;

    @Value("${my.number.less.than.ten}")
    private int          lessThanInt;

    @Value("${my.number.in.range}")
    private int          rangeInt;

    @Value("${wy.test}")
    private String       test;

    @Value("${wy.test_yml}")
    private String       testYml;

    /**
     * <pre>
     *   要加载yml中的servers 这种列表格式, 必须配置binder: ConfigurationProperties(prefix),   普通的属性不需要.
     *   两种方式加载yml中的servers列表:
     *      1, servers = new ArrayList<String>();
     *      2, 提供servers 的 getter, setter, 也可以用lombok.
     * </pre>
     */
    private List<String> servers;

    @RequestMapping("/prop1")
    public String properties1() {
        System.out.println(this.toString());
        return "this is properties1";
    }
}
