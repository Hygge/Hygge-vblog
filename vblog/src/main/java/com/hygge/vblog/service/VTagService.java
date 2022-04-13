package com.hygge.vblog.service;

import com.hygge.vblog.domain.VTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface VTagService extends IService<VTag> {

    /**
     * 根据文章id查询标签
     * @param id
     * @return
     */
    List<String> getTagNameByArticleId(Integer id);

}
