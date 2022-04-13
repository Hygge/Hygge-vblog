package com.hygge.vblog.mapper;

import com.hygge.vblog.domain.VTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hygge.vblog.domain.VTag
 */
@Mapper
public interface VTagMapper extends BaseMapper<VTag> {

    /**
     * 根据文章id查询标签名
     * @param id
     * @return
     */
    List<String> selectTagNameByArticleId(Integer id);
}




