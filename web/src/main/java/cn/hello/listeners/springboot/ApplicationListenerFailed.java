package cn.hello.listeners.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by leslie on 2018/4/30.
 */
public class ApplicationListenerFailed implements ApplicationListener<ApplicationFailedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationListenerFailed.class);

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        logger.info(getClass().getSimpleName());
    }
}
