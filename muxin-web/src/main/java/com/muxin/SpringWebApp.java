package com.muxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @Projectname: muxin
 * @Filename: SpringWebfluxApp
 * @Author: yangxz
 * @Data:2023/7/27 14:57
 * @Description:
 */

@SpringBootApplication
@EnableFeignClients
@ComponentScan(useDefaultFilters = true, basePackages = {"com.muxin.**"})
public class SpringWebApp {

    public static void main(String[] args) {
        System.out.println("availableProcessors"+Runtime.getRuntime().availableProcessors());
        SpringApplication.run(SpringWebApp.class, args);
    }

}
