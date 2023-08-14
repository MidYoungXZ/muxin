package com.muxin.system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEncrpyDto implements Serializable {
    private Long uid;
    private String username;

    /**
     * 字段加密
     */
    private String password;
}
