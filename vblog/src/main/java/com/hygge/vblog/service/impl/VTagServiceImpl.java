package com.hygge.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hygge.vblog.domain.VTag;
import com.hygge.vblog.service.VTagService;
import com.hygge.vblog.mapper.VTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
public class VTagServiceImpl extends ServiceImpl<VTagMapper, VTag>
    implements VTagService{

    @Autowired
    VTagMapper tagMapper;

    @Override
    @Transactional
    public List<String> getTagNameByArticleId(Integer id) {
        return tagMapper.selectTagNameByArticleId(id);
    }
}




