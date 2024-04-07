package com.muxin.validator.demo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Projectname: muxin
 * @Filename: Employee
 * @Author: yangxz
 * @Data:2023/11/22 11:26
 * @Description:
 */
@Data
public class Employee {
    @Valid
    @NotNull(message = "person不能为空")
    /**
     * 此处用到容器元素级别的约束: List<@Valid @NotNull Person>
     * 会校验容器内部元素是否为null，否则为null时会跳过校验
     * NotNull注解的target包含ElementType.TYPE_USE，因此NotNull可以给泛型注解
     */
    private List<@Valid @NotNull Person> people;
}
