package com.hygge.vblog.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/9 21:53
 * @description TODO
 */
@Data
public class CommentsVo implements Serializable {

    private Integer id;
    /**
     * 内容
     */
    private String content;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 评论昵称
     */
    private String username;
    /**
     * 评论者邮箱
     */
    private String email;
    /**
     * 博客地址，或者网站地址
     */
    private String address;


}
