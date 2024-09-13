package com.muxin.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @Projectname: muxin
 * @Filename: WebFluxController
 * @Author: yangxz
 * @Data:2023/7/27 15:01
 * @Description:
 */

@RestController
@RequestMapping(value = "/")
public class WebFluxController {


    @PostMapping(value = "/actuator/health")
    public Map string(@RequestBody String events) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "UP");
        hashMap.put("events.length", events.length());
        return hashMap;
    }

    @PostMapping(value = "/actuator/health/sleep")
    public Map stringS(@RequestBody String events) throws InterruptedException {
        Thread.sleep(20);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "UP");
        hashMap.put("events.length", events.length());
        return hashMap;
    }


}
