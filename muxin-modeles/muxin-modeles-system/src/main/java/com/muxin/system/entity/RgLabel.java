package com.muxin.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author 邦盛科技
 * @since 2022-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("RG_LABEL")
@ApiModel(value="RgLabel对象", description="标签表")
@KeySequence(value = "RG_LABEL_SEQ")
public class RgLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "ID_")
    private Long id;

    @ApiModelProperty(value = "标签分组ID")
    @TableField("LABEL_GROUP_ID")
    private Long labelGroupId;

    @ApiModelProperty(value = "标签名称")
    @TableField("LABEL_NAME")
    private String labelName;

    @ApiModelProperty(value = "标签编码")
    @TableField("LABEL_CODE")
    private String labelCode;

    @ApiModelProperty(value = "图模型")
    @TableField("GRAPH_ID")
    private Long graphId;

    @ApiModelProperty(value = "是否默认分组")
    @TableField("DIM")
    private String dim;

    @TableField("CREATER")
    private String creater;

    @ApiModelProperty(value = "更新人")
    @TableField("UPDATER")
    private String updater;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private Date createTime;


}
