package com.hygge.vblog.service;

import com.hygge.vblog.common.dto.PageDto;
import com.hygge.vblog.common.util.page.PagetionUtil;
import com.hygge.vblog.domain.VArticle;
import com.baomidou.mybatisplus.extension.service.IService;

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
