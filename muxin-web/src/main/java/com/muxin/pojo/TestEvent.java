package com.muxin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @Projectname: muxin
 * @Filename: TestEvent
 * @Author: yangxz
 * @Data:2023/8/23 10:28
 * @Description:
 */

@Getter
public class TestEvent extends ApplicationEvent {

    private String name;


    public TestEvent(String name) {
        super(name);
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
