package com.muxin.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Projectname: muxin
 * @Filename: People
 * @Author: yangxz
 * @Data:2023/11/22 15:36
 * @Description:
 */

@Data
public class People {

    //不管是否已婚，都需要校验的字段，groups里面指定两个分组
    @NotBlank(message = "姓名不能为空",groups = {Group.UnMarried.class, Group.Married.class})
    private String name;

    @NotNull(message = "年龄不能为空",groups = {Group.UnMarried.class, Group.Married.class})
    @Range(min = 0, max = 100, message = "年龄必须在{min}和{max}之间",groups = {Group.UnMarried.class, Group.Married.class})
    private Integer age;

    @NotNull(message = "是否已婚不能为空",groups = {Group.UnMarried.class, Group.Married.class})
    private Boolean isMarried;

    //已婚需要校验的字段
    @NotNull(message = "配偶姓名不能为空",groups = {Group.Married.class})
    private String spouseName;

    //已婚需要校验的字段
    @NotNull(message = "是否有小孩不能为空",groups = {Group.Married.class})
    private Boolean hasChild;

    //未婚需要校验的字段
    @NotNull(message = "是否单身不能为空",groups = {Group.UnMarried.class})
    private Boolean isSingle;
}