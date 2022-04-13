package com.hygge.vblog.common.vo;

import lombok.Data;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/5 20:31
 * @description TODO
 */
@Data
public class UserVo {
    private Integer id;
    /**
     * 用户名
     */
    private String userName;

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
     * 备案号
     */
    private String icp;

    /**
     * 签名
     */
    private String signature;

    /**
     * 壁纸链接
     */
    private String coverImgUrl;
    /**
     * 网站浏览量
     */
    private Integer count;


}
