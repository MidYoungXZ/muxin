package com.muxin.system.dto;

import com.muxin.common.core.annotation.CheckMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;

/**
 * @author: yangxz
 * @create: 2022/9/21
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户校验dto")
public class CheckUserVo implements Serializable {

    /**
     * 在更新时校验id
     */
    @NotNull(message = "修改的id不能为空", groups = Update.class)
    @Null(message = "新增的id必须为空", groups = Add.class)
    @ApiModelProperty(value = "新增的id",dataType = "String",name = "uid",example = "1")
    private Long uid;

    /**
     * 更新分组
     */
    public interface Update extends Default {
    }
    /**
     * 插入分组
     */
    public interface Add extends Default {
    }


    @NotBlank(message = "username不能为空")
    @Length(min = 6, max = 10)
    @ApiModelProperty(value = "新增的username",dataType = "String",name = "username",example = "admin")
    private String username;


    @NotBlank(message = "password不能为空")
    @Length(min = 6, max = 12)
    private String password;


    @NotBlank(message = "email不能为空")
    @Email(message = "email的格式不对")
    private String email;

    @CheckMobile( message = "手机号不对!!!")
    private String phone;
}
