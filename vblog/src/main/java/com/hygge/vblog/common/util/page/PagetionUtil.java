package com.hygge.vblog.common.util.page;

import lombok.Data;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/7 10:24
 * @description TODO    分页工具类
 */
@Data
public class PagetionUtil<T> {

    /**
     * 数据
     */
    private T data;
    /**
     * 总计
     */
    private Integer count;
    /**
     * 分页数量
     */
    private Integer pages;

    public PagetionUtil() {
    }

    public PagetionUtil(T data, Integer count, Integer pages) {
        this.data = data;
        this.count = count;
        this.pages = pages;
    }

}
