package com.hygge.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hygge.vblog.common.vo.CommentsVo;
import com.hygge.vblog.domain.VComment;
import com.hygge.vblog.service.VCommentService;
import com.hygge.vblog.mapper.VCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class VCommentServiceImpl extends ServiceImpl<VCommentMapper, VComment>
    implements VCommentService{

    @Autowired
    VCommentMapper commentMapper;

    @Override
    public List<CommentsVo> getAllComments(Integer id) {



        return commentMapper.selectAllByArticleIdAfter(id);
    }
}




