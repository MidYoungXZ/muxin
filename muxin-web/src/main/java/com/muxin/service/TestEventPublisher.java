package com.muxin.service;

import com.muxin.pojo.TestEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @Projectname: muxin
 * @Filename: TestEventPublisher
 * @Author: yangxz
 * @Data:2023/8/23 10:29
 * @Description:
 */
@Component
public class TestEventPublisher implements ApplicationEventPublisherAware {


    ApplicationEventPublisher applicationEventPublisher;

    /**
     * 所有事件都可以发
     * @param event
     */
    public void sendEvent(TestEvent event) {
        //调用底层API发送事件
        applicationEventPublisher.publishEvent(event);
    }




    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher=applicationEventPublisher;
    }
}
