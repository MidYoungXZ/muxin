package com.muxin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class SpringTest {

    @Autowired
    private ApplicationContext applicationContext;


    @Test
    public void test(){

        Map<String, WebServerFactoryCustomizer> beansOfType = applicationContext.getBeansOfType(WebServerFactoryCustomizer.class);
        Map<String, EmbeddedWebServerFactoryCustomizerAutoConfiguration> beansOfType1 = applicationContext.getBeansOfType(EmbeddedWebServerFactoryCustomizerAutoConfiguration.class);
        System.out.println(beansOfType);
        System.out.println(beansOfType1);
    }

}