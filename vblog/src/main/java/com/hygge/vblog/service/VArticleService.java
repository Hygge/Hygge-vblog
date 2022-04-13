package com.hygge.vblog.service;

import com.hygge.vblog.common.dto.BlogsDto;
import com.hygge.vblog.common.dto.PageDto;
import com.hygge.vblog.common.util.PagetionUtil;
import com.hygge.vblog.domain.VArticle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface VArticleService extends IService<VArticle> {

    /**
     * 分页获取所有文章信息
     * @param pageDto
     */
    PagetionUtil getAllPages(PageDto pageDto);

    /**
     * 保存文章返回主键id
     * @param vArticle
     * @return
     */
    Integer saveId(VArticle vArticle);
}
