package com.muxin.validator.web;

import com.muxin.validator.demo.Group;
import com.muxin.validator.demo.People;
import com.muxin.validator.demo.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Projectname: muxin
 * @Filename: BaseBuildingController
 * @Author: yangxz
 * @Data:2023/11/22 15:44
 * @Description:
 */
@RestController
@RequestMapping("/base/building")
public class PersonController {

    /**
     * 验证不通过抛出 `MethodArgumentNotValidException`
     */
    @PostMapping(value="/insert")
    public People insert(@RequestBody @Validated(Group.Married.class) People dto){
        System.err.println(dto);
        return dto;
    }
}
