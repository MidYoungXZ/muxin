package com.muxin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muxin.pojo.SpringDocGetObject;
import com.muxin.pojo.SpringDocPostObject;
import com.muxin.pojo.TestStaticObj;
import com.muxin.service.BsfitGraphResponse;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Projectname: muxin
 * @Filename: SpringDocDemoController
 * @Author: yangxz
 * @Data:2023/10/17 11:13
 * @Description:
 */

@Tag(name = "SpringDoc示例名称",description = "SpringDoc示例接口描述")
@RestController
public class SpringDocDemoController {


    @Value("${test.name}")
    private String name;


    @Operation(summary = "get无参请求")
    @GetMapping(value = "/get")
    public String get() {
        System.out.println(TestStaticObj.name);
        System.out.println(name);
        return "success";
    }

    @Operation(summary = "get有参请求")
    @Parameter(name = "id",description = "主键id",required = true)
    @GetMapping(value = "/getParam")
    public String getParam(@RequestParam String id) {
        return "success";
    }


    @Operation(summary = "get多参实体请求")
    @GetMapping(value = "/getObject")
    public String getObject(@ParameterObject SpringDocGetObject getObject) {
        System.err.println("getObject:"+getObject);
        return "success";
    }

    @Operation(summary = "post请求")
    @PostMapping(value = "/post")
    public String post(@RequestBody SpringDocPostObject postObject) {
        System.err.println("postObject:"+postObject);
        return "success";
    }


    @Operation(summary = "put请求")
    @PutMapping(value = "/put")
    public String put(@RequestBody SpringDocPostObject postObject) {
        System.err.println("postObject:"+postObject);
        return "success";
    }

}
