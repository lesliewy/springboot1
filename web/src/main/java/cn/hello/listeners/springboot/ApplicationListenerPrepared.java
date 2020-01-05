package cn.hello.listeners.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by leslie on 2018/4/30.
 */
public class ApplicationListenerPrepared implements ApplicationListener<ApplicationPreparedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationListenerPrepared.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        logger.info(getClass().getSimpleName());
    }
}
