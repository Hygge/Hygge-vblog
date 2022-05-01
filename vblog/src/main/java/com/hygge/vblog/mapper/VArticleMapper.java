package com.hygge.vblog.mapper;

import com.hygge.vblog.common.dto.BlogsDto;
import com.hygge.vblog.domain.VArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.hygge.vblog.domain.VArticle
 */
@Mapper
public interface VArticleMapper extends BaseMapper<VArticle> {


    /**
     * 分页查询所有文章
     * @param current
     * @param pageSize
     * @return
     */
    List<BlogsDto> selectAll(@Param("current")Long current, @Param("pageSize")Long pageSize);

    List<BlogsDto> selectAllFlag(@Param("current")Long current, @Param("pageSize")Long pageSize, @Param("flag")Integer flag);

    /**
     * 保存文章并返回主键id
     * @param vArticle
     * @return
     */
    Integer insertFanId(VArticle vArticle);


}




