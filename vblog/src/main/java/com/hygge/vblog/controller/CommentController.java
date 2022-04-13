package com.hygge.vblog.controller;

import com.hygge.vblog.common.result.Result;
import com.hygge.vblog.service.VCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/10 15:43
 * @description TODO
 */
@RestController
public class CommentController {

    @Autowired
    VCommentService commentService;


    /**
     * 删除评论
     * @param id
     * @return
     */
    @PostMapping("/delComment/{id}")
    public Result delComment(@PathVariable(name = "id")Integer id){
        commentService.removeById(id);
        return Result.ok();
    }


}
