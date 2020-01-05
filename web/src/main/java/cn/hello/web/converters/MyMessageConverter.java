package cn.hello.web.converters;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import cn.hello.pojo.DemoObj;

/**
 * <pre>
 *   自定义message myMessageConverter
 * </pre>
 *
 * Created by leslie on 2018/5/1.
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    public MyMessageConverter(){
        // 新建一个我们自定义的媒体类型application/x-wisely
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 表明本HttpMessageConverter 只处理DemoObj 这个类。
        return DemoObj.class.isAssignableFrom(clazz);
    }

    /**
     * <pre>
     *   重写readlntenal 方法，处理请求的数据。
     *   代码表明我们处理由“四”隔开的数据，并转成 DemoObj 的对象。
     * </pre>
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz,
                                   HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");

        return new DemoObj(new Long(tempArr[0]), tempArr[1]);
    }

    /**
     * <pre>
     *    重写writeInternal，处理如何输出数据到response。
     *    此例中，我们在原样输出前面加上"hello ："。
     * </pre>
     */
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage outputMessage) throws IOException,
                                                                                   HttpMessageNotWritableException {
        String out = "hello: " + demoObj.getId() + "-" + demoObj.getName();
        outputMessage.getBody().write(out.getBytes());
    }
}
