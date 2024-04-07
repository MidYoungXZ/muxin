package com.muxin.demo.controller;



import com.muxin.demo.pojo.dto.CheckUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description:
 */
@RestController
@Validated
@Tag(name = "用户校验前端控制器",description = "用户校验前端控制器")
@Slf4j
public class CheckController {

    @Operation( method = "getById",summary = "根据id获取用户信息")
    @Parameters( @Parameter (name = "id",required = true))
    @GetMapping("/checkUser/getById/{id}")
    public CheckUserVo getById(@PathVariable("id") @Min(5) @Max(19) Long id) {
        log.info("根据id:{}获取用户信息",id);
        return new CheckUserVo(id, "username", "123456", "123@qq.com", "13812341239");
    }

    @Operation(summary = "根据邮箱获取用户信息")
    @Parameters( @Parameter (name = "email",required = true))
    @GetMapping("/checkUser/getByEmail")
    public CheckUserVo getByEmail(@RequestParam @NotBlank(message = "邮箱不能为空") @Email String email) {
        return null;
    }

    @PostMapping("/checkUser/addUser")
    public boolean addUser(@Validated(CheckUserVo.Add.class) @RequestBody CheckUserVo checkUserVo) {
        return true;
    }

    /**
     * 【分组校验】@Validated注解实现
     *
     * @param checkUserVo 入参
     * @return true/false 成功或失败
     */
    @PostMapping("checkUser/updateUser")
    public boolean updateUser(@Validated(CheckUserVo.Update.class) @RequestBody CheckUserVo checkUserVo) {
        // 具体业务逻辑调用
        return true;
    }
}
