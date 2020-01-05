package cn.hello.listeners.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by leslie on 2018/4/30.
 */
public class ApplicationListenerStarted implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationListenerFailed.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.info(getClass().getSimpleName());
    }

}
