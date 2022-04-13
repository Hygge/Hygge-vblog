package com.hygge.vblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hygge.vblog.common.dto.BlogsDto;
import com.hygge.vblog.common.dto.PageDto;
import com.hygge.vblog.common.util.PagetionUtil;
import com.hygge.vblog.common.util.RedisUtil;
import com.hygge.vblog.domain.VArticle;
import com.hygge.vblog.domain.VComment;
import com.hygge.vblog.service.VArticleService;
import com.hygge.vblog.mapper.VArticleMapper;
import com.hygge.vblog.service.VCommentService;
import com.hygge.vblog.service.VTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class VArticleServiceImpl extends ServiceImpl<VArticleMapper, VArticle>
    implements VArticleService{

    @Autowired
    VTagService tagService;
    @Autowired
    VCommentService commentService;
    @Autowired
    VArticleMapper vArticleMapper;

    /**
     * 查询数据库
     * @param pageDto
     * @return
     */
    @Transactional
    public List<BlogsDto> getAllPage(PageDto pageDto){
        Integer current = (pageDto.getCurrent() - 1) * pageDto.getPageSize();
        if (pageDto.getFlag() != null){
            return vArticleMapper.selectAllFlag(current, pageDto.getPageSize(), pageDto.getFlag());
        }else {
            return vArticleMapper.selectAll(current, pageDto.getPageSize());
        }
    }

    @Override
    public PagetionUtil getAllPages(PageDto pageDto) {
        PagetionUtil data = new PagetionUtil();
        //查出所有文章信息
        List<BlogsDto> blogsDtoList = getAllPage(pageDto);
        //根据文章id查出所有标签并封装, 和统计评论数量封装
        blogsDtoList.forEach( blogsDto -> {
            blogsDto.setTag(tagService.getTagNameByArticleId(blogsDto.getId()));
            int content = (int) commentService.count(new QueryWrapper<VComment>().eq("article_id", blogsDto.getId()));
            blogsDto.setContent(content);
        });
        Integer count = 0;
        if ( pageDto.getFlag() == null){
            count = (int)count();
        }else {
            count = (int)count(new QueryWrapper<VArticle>().eq("status", pageDto.getFlag()));
        }

        Integer pages =(count + pageDto.getPageSize() - 1) / pageDto.getPageSize();
        data.setData(blogsDtoList);
        data.setPages(pages);
        data.setCount(count);
        return data;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer saveId(VArticle vArticle) {

        return vArticleMapper.insertFanId(vArticle);
    }


}




