package com.hygge.vblog.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/3/7 16:01
 * @description TODO    注册数据封装
 */
@Data
public class RegisterDTO implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avator;

    /**
     * QQ
     */
    private String qq;

    /**
     * 邮箱验证吗
     */
    private String code;


}
