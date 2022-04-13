package com.hygge.vblog.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/6 13:06
 * @description TODO
 */
@Data
public class BlogDto implements Serializable {

    private Integer id;
    private String title;
    private String description;
    private String content;
    private Integer userId;
    private Integer categorysId;
    private List<String> tag;

}
