package com.muxin.validator.demo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @Projectname: muxin
 * @Filename: Person
 * @Author: yangxz
 * @Data:2023/11/22 11:22
 * @Description:
 */

@GroupSequence({ Group.UnMarried.class, Group.Married.class })
@Data
public class Person{

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Range(min = 0, max = 100, message = "年龄必须在{min}和{max}之间")
    private Integer age;

    @NotNull(message = "是否已婚不能为空")
    private Boolean isMarried;

    @NotEmpty(message = "家庭成员不能为空")
    private Collection collection;

    @NotEmpty(message = "个人学历不能为空")
    private String[] array;

    @Email
    private String email;

}
