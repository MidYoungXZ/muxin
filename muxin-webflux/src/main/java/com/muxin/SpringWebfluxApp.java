package com.muxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Projectname: muxin
 * @Filename: SpringWebfluxApp
 * @Author: yangxz
 * @Data:2023/7/27 14:57
 * @Description:
 */

@SpringBootApplication(exclude = {EmbeddedWebServerFactoryCustomizerAutoConfiguration.class})
@ComponentScan(basePackages = "com.muxin.controller")
public class SpringWebfluxApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApp.class, args);
    }

}
