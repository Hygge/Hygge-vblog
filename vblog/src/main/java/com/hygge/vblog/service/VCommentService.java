package com.hygge.vblog.service;

import com.hygge.vblog.common.vo.CommentsVo;
import com.hygge.vblog.domain.VComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface VCommentService extends IService<VComment> {

    /**
     * 获取所有评论
     * @param id
     * @return
     */
    List<CommentsVo> getAllComments(Integer id);

}
