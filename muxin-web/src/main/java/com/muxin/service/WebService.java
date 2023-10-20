package com.muxin.service;

import com.muxin.pojo.TestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Projectname: muxin
 * @Filename: WebService
 * @Author: yangxz
 * @Data:2023/8/23 10:25
 * @Description:
 */
@Service
public class WebService {



    @EventListener
    public void onEvent(TestEvent testEvent){
        System.out.println("===== WebService ====感知到事件"+testEvent);
        System.out.println(testEvent.getName());
    }





}
