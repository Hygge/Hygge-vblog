package com.hygge.vblog.common.dto;

import lombok.Data;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/6 15:37
 * @description TODO
 */
@Data
public class PageDto {
    /**
     * 默认当前分页1
     */
    private Integer current;
    /**
     * 默认当前页面数据大小5
     */
    private Integer pageSize;

    /**
     *  1已发布 0回收站
     */
    private Integer flag;

}
