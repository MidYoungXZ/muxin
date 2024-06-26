package com.muxin.demo.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@KeySequence(value = "user_seq")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_")
    private Integer id;

    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    @TableField("email")
    private String email;


}
