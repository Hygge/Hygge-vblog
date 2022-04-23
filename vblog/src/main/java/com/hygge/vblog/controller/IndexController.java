package com.hygge.vblog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hygge.vblog.common.annotation.OtherLog;
import com.hygge.vblog.common.dto.PageDto;
import com.hygge.vblog.common.result.Result;
import com.hygge.vblog.common.util.ImgUtil;
import com.hygge.vblog.common.util.RedisUtil;
import com.hygge.vblog.common.vo.ArticleVo;
import com.hygge.vblog.common.vo.UserVo;
import com.hygge.vblog.domain.*;
import com.hygge.vblog.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/5 20:59
 * @description TODO
 */
@Slf4j
@RestController
public class IndexController {

    @Autowired
    VTagService tagService;
    @Autowired
    VCategorysService categorysService;
    @Autowired
    VArticleService articleService;
    @Autowired
    VCommentService commentService;
    @Autowired
    VTagRelationshipService tagRelationshipService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    VUserService userService;
    @Autowired
    ImgUtil imgUtil;
    //        文档链接  https://blog.csdn.net/weixin_43247803/article/details/113666136


    /**
     * 保存评论
     * @param comment
     * @return
     */
    @PostMapping("/saveComment")
    public Result saveComment(@RequestBody VComment comment){
        boolean b = commentService.save(comment);
        if (b){
            return Result.ok();
        }
        return Result.no();
    }

    /**
     * 获取所有评论
     * @param id
     * @return
     */
    @GetMapping("/getComments/{id}")
    public Result getComments(@PathVariable(name = "id") Integer id){

        return Result.ok(commentService.getAllComments(id));
    }


    /**
     * 获取所有标签
     * @return
     */
    @OtherLog(logName = "获取所有的标签")
    @GetMapping("/getAllTag")
    public Result getAllTag(){
        List<VTag> tags = tagService.getBaseMapper().selectList(new QueryWrapper<VTag>().orderByDesc("id"));
        return Result.ok(tags);
    }

    /**
     * 获取所有分类
     * @return
     */
    @GetMapping("/getAllCategorys")
    public Result getAllCategorys(){
        List<VCategorys> categorys = categorysService.getBaseMapper().selectList(new QueryWrapper<VCategorys>().orderByDesc("id"));
        return Result.ok(categorys);
    }

    /**
     * 首页获取个人信息
     *
     * @return
     */
    @GetMapping("/userInfo")
    public Result getUserInfo() {
        List<VUser> users = userService.getBaseMapper().selectList(new QueryWrapper<VUser>().orderByAsc("created_date"));
        VUser user = users.get(0);
        String url = imgUtil.getWallpaper();
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo, "password", "createdDate");
        userVo.setCoverImgUrl(url);
        return Result.ok(userVo);
    }

    /**
     * 前台获取所有文章 游客访问
     * @param pageDto
     * @return
     */
    @PostMapping("/getAllPage")
    public Result getAllPage(@RequestBody PageDto pageDto, HttpServletRequest request){
        //网站游官访问量 ip有效时间一天 浏览人数+1
        String ip = request.getRemoteHost();
        Integer i = (Integer) redisUtil.get(ip);
        if (i == null ){
            boolean b = redisUtil.set(ip, 1, 60*60*24);
            if (b){
                log.error("redis出现异常---");
            }
            VUser user = userService.getBaseMapper().selectList(new QueryWrapper<VUser>().orderByAsc("id")).get(0);
            user.setCount(user.getCount() + 1);
            userService.updateById(user);
        }
        int flag = 1;
        pageDto.setFlag(flag);
        return  Result.ok(articleService.getAllPages(pageDto));
    }

    /**
     * 后台获取所有文章
     * @param pageDto
     * @return
     */
    @PostMapping("/admin/getAllPage")
    public Result getAllPages(@RequestBody PageDto pageDto){
        return  Result.ok(articleService.getAllPages(pageDto));
    }
    /**
     * 回收站获取所有文章
     * @param pageDto
     * @return
     */
    @PostMapping("/admin/getAllHPage")
    public Result getAllHPages(@RequestBody PageDto pageDto){
        int flag = 0;
        pageDto.setFlag(flag);
        return Result.ok(articleService.getAllPages(pageDto));
    }



    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    @GetMapping("/blog/{id}")
    public Result getBlog(@PathVariable(name = "id")Integer id){
        VArticle byId = articleService.getById(id);
        ArticleVo articleVo = new ArticleVo();
        BeanUtil.copyProperties(byId, articleVo);
        //查出文章关联标签id，查出标签名称， 查出分类名称
        articleVo.setTag(tagService.getTagNameByArticleId(byId.getId()));
        articleVo.setCategory(categorysService.getById(byId.getCategorysId()).getName());
        //文章阅读量+1
        int numberViews = byId.getNumberView() + 1;
        byId.setNumberView(numberViews);
        articleService.update(byId, new QueryWrapper<VArticle>().eq("id", byId.getId()));
        return Result.ok(articleVo);
    }



}
