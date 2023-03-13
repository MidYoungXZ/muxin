package com.muxin.system.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author 邦盛科技
 * @since 2022-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_USER")
@ApiModel(value="SysUser对象", description="系统用户表")
@KeySequence("SYS_USER_SEQ")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableId(value = "USERNAME")
    private String username;

    @ApiModelProperty(value = "SHA加密后的密码")
    @TableField("PASSWORD_")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    @TableField("REALNAME")
    private String realname;

    @ApiModelProperty(value = "头衔")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "机构ID")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "状态 1-启用 0-停用N0为失效")
    @TableField("IS_ENABLED")
    private Integer isEnabled;

    @ApiModelProperty(value = "多地登录 1-多地登录 0-非多地登录")
    @TableField("MULTILOGIN_ENABLED")
    private Integer multiloginEnabled;

    @ApiModelProperty(value = "机构路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "电子邮件")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "移动电话")
    @TableField("MOBILE")
    private String mobile;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    @TableField("UPDATE_TIME")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_BY")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField("UPDATE_BY")
    private String updateBy;

    @ApiModelProperty(value = "页数")
    @TableField("PAGE_SIZE")
    private Long pageSize;

    @ApiModelProperty(value = "密码上次修改时间")
    @TableField("LAST_PWD_TIME")
    private Date lastPwdTime;

    @ApiModelProperty(value = "解锁时间")
    @TableField("UNLOCK_TIME")
    private Date unlockTime;

    @ApiModelProperty(value = "密码输错次数")
    @TableField("PWD_ERROR_TIMES")
    private BigDecimal pwdErrorTimes;

    @ApiModelProperty(value = "数据权限类型(0-BIZ,1-PACKAGE)")
    @TableField("DATA_AUTH_TYPE")
    private BigDecimal dataAuthType;

    @ApiModelProperty(value = "处室标签")
    @TableField("OFFICE_AUTH")
    private String officeAuth;


}
