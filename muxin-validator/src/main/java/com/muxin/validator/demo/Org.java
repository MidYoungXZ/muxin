package com.muxin.validator.demo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Projectname: muxin
 * @Filename: Org
 * @Author: yangxz
 * @Data:2023/11/22 11:25
 * @Description:
 */
@Data
public class Org {
    @NotNull
    private Integer id;

    @Valid  //如果此处不用Valid注解，则不会去校验Employee对象的内部字段
    @NotNull(message = "employee不能为空")
    private Employee employee;
}
