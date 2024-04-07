package com.muxin.demo.controller;


import com.muxin.common.core.annotation.NotControllerResponseAdvice;
import com.muxin.common.core.common.Result;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description: 特别的controller
 */
//@RestController
public class SpecialController {

    /**
     * 就想只返回个success 不需要包装成统一格式
     */
    @NotControllerResponseAdvice
    @GetMapping("/isLive")
    public String special() {
        return "success";
    }

    /**
     * response是ResultVo类型
     */
    @NotControllerResponseAdvice
    @GetMapping("/result")
    public Result result() {
        return Result.success();
    }
}
