package com.muxin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @Projectname: muxin
 * @Filename: WebFluxController
 * @Author: yangxz
 * @Data:2023/7/27 15:01
 * @Description:
 */

@RestController
@RequestMapping(value = "/webflux")
public class WebFluxController {



    @GetMapping(value = "/string")
    public Mono<String> string() {
        return Mono.just("success").delayElement(Duration.ofMillis(50));
    }



}
