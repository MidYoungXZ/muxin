package com.muxin.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @Projectname: muxin
 * @Filename: PageVO
 * @Author: yangxz
 * @Data:2023/10/11 14:53
 * @Description:
 */
@Data
@ToString
public class PageReqVO {

    private int start;

    private int limit;

}
