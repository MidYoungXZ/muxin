package com.muxin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Projectname: muxin
 * @Filename: WebFluxController
 * @Author: yangxz
 * @Data:2023/7/27 15:01
 * @Description:
 */

@RestController
@RequestMapping(value = "/web")
public class WebController {


    @GetMapping(value = "/testAPi")
    public String testAPi() {
        return "success";
    }

    @GetMapping(value = "/string",params = {"name"})
    public String string(String name) {
        try {
            System.out.println("string:"+name);
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

}
