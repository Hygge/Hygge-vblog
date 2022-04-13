package com.hygge.vblog.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/7 19:20
 * @description TODO
 */
@Data
public class UpdateArticleStatusDto implements Serializable {

    private Integer id;
    private Integer status;

}
