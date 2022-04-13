package com.hygge.vblog.mapper;

import com.hygge.vblog.common.vo.CommentsVo;
import com.hygge.vblog.domain.VComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hygge.vblog.domain.VComment
 */
@Mapper
public interface VCommentMapper extends BaseMapper<VComment> {

    /**
     * 根据文章id查询所有文章评论
     * @param id
     * @return
     */
    List<CommentsVo> selectAllByArticleIdAfter(Integer id);

}




