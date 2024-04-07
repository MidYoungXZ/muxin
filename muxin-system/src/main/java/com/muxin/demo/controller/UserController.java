package com.muxin.demo.controller;


import com.muxin.common.core.annotation.Prevent;
import com.muxin.demo.service.OtherService;
import com.muxin.demo.pojo.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description:
 */
//@RestController
public class UserController {

    private OtherService userService;

    @Autowired
    public UserController(OtherService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/getById/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/user/getList")
    @Prevent(value = "10",message = "10秒内不能重复请求")
    public List<UserDto> getList(HttpServletRequest request) {
        return userService.getList();
    }

    @PostMapping("/user/addUser")
    @Prevent(value = "10",message = "10秒内不能重复请求")
    public boolean addUser(HttpServletRequest request, @RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping("/user/getUserName/{id}")
    public String getUserName(@PathVariable("id") Long id) {
        return userService.getUserName(id);
    }
}
