package cn.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.hello.pojo.DemoObj;

/**
 * <pre>
 *    普通的Controller, 接收并处理http请求;
 * </pre>
 * 
 * Created by leslie on 2018/5/1.
 */

@Controller
public class ConverterController {

    /**
     * <pre>
     *    指定返回的媒体类型为我们自定义的媒体类型application/x-wisely
     *    使用postman 等工具调用时:
     *       post url: http://localhost:7181/convert1,
     *       headers: content-type application/x-wisely,
     *       body: raw 中的text 格式, 内容为1-sdsds 即可.
     * </pre>
     */
    @RequestMapping(method = RequestMethod.POST, value = "/convert1", produces = "application/x-wisely")
    // ResponseBody 直接将返回放入http response, 不需要经过view resolver.
    @ResponseBody
    public DemoObj converter1(@RequestBody DemoObj obj) {
        System.out.println("this is converter1...");
        return obj;
    }

    // 没有 @ResponseBody, 返回的是一个view.
    @RequestMapping(method = RequestMethod.GET, value = "/page2", produces = "text/html")
    public String page1() {
        System.out.println("this is page1...");
        return "page1";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/convert3", produces = "application/x-java-serialization")
    @ResponseBody
    public String convert3(String input) {
        System.out.println("this is convert3...");
        return input;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/convert4", produces = "application/json")
    @ResponseBody
    public String convert4(@RequestParam String input) {
        System.out.println("this is convert3...");
        return input;
    }
}
