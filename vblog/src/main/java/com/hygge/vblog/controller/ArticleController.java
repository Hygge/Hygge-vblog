package com.hygge.vblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hygge.vblog.common.dto.BlogDto;
import com.hygge.vblog.common.dto.UpdateArticleStatusDto;
import com.hygge.vblog.common.result.Result;
import com.hygge.vblog.domain.VArticle;
import com.hygge.vblog.domain.VTag;
import com.hygge.vblog.domain.VTagRelationship;
import com.hygge.vblog.service.VArticleService;
import com.hygge.vblog.service.VTagRelationshipService;
import com.hygge.vblog.service.VTagService;
import com.hygge.vblog.shiro.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/6 11:12
 * @description TODO
 */
@Slf4j
@RestController
public class ArticleController {

    @Autowired
    VArticleService articleService;
    @Autowired
    VTagRelationshipService tagRelationshipService;
    @Autowired
    VTagService tagService;

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@RequestBody BlogDto blogDto){
        //获取当前状态userId
        AccountProfile principal = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        Integer id = principal.getId();
        VArticle temp = new VArticle();
        VArticle article = articleService.getById(blogDto.getId());
        //获取文章的标签id
        ArrayList<Integer> tag = new ArrayList<>();
        blogDto.getTag().forEach(item -> {
            VTag one = tagService.getOne(new QueryWrapper<VTag>().eq("tag_name", item));
            tag.add(one.getId());
        });
        if (article != null){
            //编辑文章  保存默认发布
            //现已保存的标签id记录删除，新增修改后的标签id记录
            List<VTagRelationship> tagRelationshipList = tagRelationshipService.getBaseMapper()
                    .selectList(new QueryWrapper<VTagRelationship>().eq("article_id", blogDto.getId()));
            tagRelationshipService.removeBatchByIds(tagRelationshipList.stream()
                    .map(VTagRelationship :: getId).collect(Collectors.toList()));
            //添加
            tag.forEach( item -> {
                VTagRelationship tagRelationship = new VTagRelationship();
                tagRelationship.setArticleId(article.getId());
                tagRelationship.setTagId(item);
                tagRelationshipService.save(tagRelationship);
            });
            //更新文章
            article.setContext(blogDto.getContent());
            article.setDescription(blogDto.getDescription());
            article.setTitle(blogDto.getTitle());
            article.setCategorysId(blogDto.getCategorysId());
            articleService.update(article, new QueryWrapper<VArticle>().eq("id", article.getId()));

        }else {
            //新增文章
            temp.setUserId(id);
            temp.setTitle(blogDto.getTitle());
            temp.setContext(blogDto.getContent());
            temp.setCategorysId(blogDto.getCategorysId());
            temp.setDescription(blogDto.getDescription());
            temp.setCreatedDate(new Date());

            articleService.saveId(temp);
            for (Integer i :
                    tag) {
                VTagRelationship ta = new VTagRelationship();
                ta.setArticleId(temp.getId());
                ta.setTagId(i);
                tagRelationshipService.save(ta);
            }
        }
        return Result.ok();
    }


    /**
     * 修改文章状态
     * @param statusDto
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/modifyStatus")
    public Result modifyStatus(@RequestBody UpdateArticleStatusDto statusDto){
        VArticle byId = articleService.getById(statusDto.getId());
        byId.setStatus(statusDto.getStatus());
        boolean flag = articleService.update(byId, new QueryWrapper<VArticle>().eq("id", statusDto.getId()));
        if (flag){
            return Result.ok();
        }
        return Result.no();
    }

    /**
     * 删除一篇文章
     * @param id
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/delArticle/{id}")
    public Result delArticle(@PathVariable(name = "id") Integer id){
        if (id == null){
            return Result.no("文章id不为空");
        }
        articleService.removeById(id);
        return Result.ok();
    }


}
