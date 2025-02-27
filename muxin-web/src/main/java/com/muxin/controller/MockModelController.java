package com.muxin.controller;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectname: muxin
 * @filename: MockModelController
 * @author: yangxz
 * @data:2024/10/11 10:50
 * @description:
 */
@RestController
public class MockModelController {


    @GetMapping(value = "/mock/model")
    public Map<String, Double> model1(@RequestParam String phone) {
        HashMap<String, Double> resultMap = new HashMap<>();
        if (ObjectUtils.isEmpty(phone)) {
            return resultMap;
        }
        double v = 100d + Double.parseDouble(phone.toString());
        resultMap.put("phone", v);
        return resultMap;
    }

    @GetMapping(value = "/mock/model2")
    public Map<String, Object> model1() {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("success",1);
        HashMap<String, String> data = new HashMap<>();
        data.put("probability","1,2,4");
        resultMap.put("data",data);
        return resultMap;
    }

    @GetMapping(value = "/mock/model3")
    public Map<String, Object> model3(@RequestParam String phone) {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("success",1);
        HashMap<String, String> data = new HashMap<>();
        data.put("probability","1,2,4");
        resultMap.put("data",data);
        return resultMap;
    }


    @PostMapping(value = "/mock/model4")
    public Map<String, Object> model4(@RequestParam String phone, @RequestBody Map<String, String> requestData) {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("success",1);
        HashMap<String, String> data = new HashMap<>(requestData);
        data.put("probability","1,2,4");
        resultMap.put("data",data);
        return resultMap;
    }






}
