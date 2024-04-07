package com.muxin.demo.controller;



import com.muxin.demo.pojo.dto.UserEncrpyDto;
import com.muxin.demo.service.UserEncryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//@RestController
public class UserEncryController {

    private UserEncryService userService;

    @Autowired
    public UserEncryController(UserEncryService userService) {
        this.userService = userService;
    }

    @GetMapping("/userEncry/getById/{id}")
    public UserEncrpyDto getById(@PathVariable("id") Long id) {
        UserEncrpyDto userDto = userService.getById(id);
        System.out.println("controller 查到的：" + userDto.getPassword());
        return userDto;
    }

    @PostMapping("/userEncry/addUser")
    public boolean addUser(HttpServletRequest request, @RequestBody UserEncrpyDto userDto) {
        System.out.println("controller 传递进来的参数是:" + userDto.toString());
        return userService.addUser(userDto);
    }

}
