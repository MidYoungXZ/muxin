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



    @GetMapping(value = "/string")
    public String string() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }



}
