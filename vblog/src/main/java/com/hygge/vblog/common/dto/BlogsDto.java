package com.hygge.vblog.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/6 18:06
 * @description TODO
 */
@Data
public class BlogsDto {
    /**
     *    id: '',
     *         title: '测试片',
     *         status: 1,
     *         category: '默认分类',
     *         tag: '普陀区',
     *         content: 20,
     *         numberView: 200333,
     *         createdDate:'20220101',
     */
    private Integer id; //文章id
    private String title;
    private String description;
    private String context;
    private String category;
    private Integer categoryId;
    private List<String> tag;   //标签名
    private Integer content;    //评论数量
    private Integer numberView;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;
    private Integer status;


}
