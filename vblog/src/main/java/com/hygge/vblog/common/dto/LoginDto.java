package com.hygge.vblog.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/5 20:25
 * @description TODO
 */
@Data
public class LoginDto implements Serializable {

    private String userName;
    private String password;

}
