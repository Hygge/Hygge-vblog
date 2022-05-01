package com.hygge.vblog.common.dto;

import lombok.Data;

/**
 * @Classname ImgDTO
 * @Description TODO   接收图片查询参数
 * @Version 1.0.0
 * @Date 2022/5/1 15:54
 * @Author hygge
 */
@Data
public class ImgDTO  extends PageDto{

    private Integer id;
    /**
     * 文件名关键字
     */
    private String fileName;

    /**
     * 存储类型 本地 云
     */
    private Integer localOrCloud;

    /**
     * 后缀
     */
    private String fileSuffixName;

}
