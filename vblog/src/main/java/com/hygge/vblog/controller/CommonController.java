package com.hygge.vblog.controller;

import com.hygge.vblog.common.result.Result;
import com.hygge.vblog.common.util.ImgUtil;
import com.hygge.vblog.common.util.JwtUtil;
import com.hygge.vblog.common.util.OSSUtil;
import com.hygge.vblog.common.util.RedisUtil;
import com.hygge.vblog.service.VUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname CommonController
 * @Description TODO   暂时作为工具请求
 * @Version 1.0.0
 * @Date 2022/4/22 20:44
 * @Author hygge
 */
@Slf4j
@RestController
public class CommonController {

    @Autowired
    OSSUtil ossUtil;
    @Autowired
    VUserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Value("${jwt.header}")
    private String header;
    /**
     * 图片默认保存文件夹
     */
    @Value("${img.address}")
    private String address;

    @Value("${fromEmail}")
    private String fromEmail;

    @Autowired
    ImgUtil imgUtil;
    //        文档链接  https://blog.csdn.net/weixin_43247803/article/details/113666136
    @Autowired
    RedisUtil redisUtil;



    /**
     * 上传图片 保存oss服务器
     *
     * @param upload
     * @return
     * @throws IOException
     */
    @RequiresAuthentication
    @PostMapping("/uploadImage")
    public Result uploadImage(@RequestParam(name = "multipartFile") MultipartFile upload, @RequestParam(name = "fileName") String fileName) throws IOException {
        if (upload.isEmpty() || upload == null) {
            return Result.no("图片不为空");
        }
        String url = ossUtil.upload(upload, address, fileName);
        if (StringUtils.isBlank(url)) {
            return Result.no("图片上传失败");
        }
        log.info("上传一个图片：{}", url);
        return Result.ok(url);
    }



    /**
     * 上传图片
     *
     * @param upload
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/upImg")
    public Result upImg(MultipartFile upload) {
        if (upload.isEmpty() || upload == null) {
            return Result.no("图片不为空");
        }
        String url = ossUtil.upload(upload, address, "as");
        if (StringUtils.isBlank(url)) {
            return Result.no("图片上传失败");
        }
        log.info("上传一个图片：{}", upload.getName());
        return Result.ok(url);
    }

    /**
     * 获取验证码
     *
     * @param email
     * @param request
     * @return
     */
    @PostMapping("/getCode")
    public Result getCode(String email, HttpServletRequest request) {
        //校验邮箱格式
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(email);
        if (StringUtils.isBlank(email) || StringUtils.isEmpty(email) || !matcher.matches()) {
            return Result.no("邮箱格式不正确");
        }
        //限制ip次数，以防恶意获取，自增
        String ip = request.getRemoteHost();
        String redisKey = "ip:" + ip;
        Long i = redisUtil.incr(redisKey, 1L);
        if (i == null || i.equals(0)) {
            redisUtil.expire(redisKey, 24 * 60 * 60 * 1000L);
        }
        if (i > 4) {
            return Result.no("今日验证码次数已用尽");
        }
        //发送验证码，保存redis
        String flag = "博客H云验证码";
        userService.sendEmailCode(email, flag, fromEmail);

        return Result.ok("发送成功");
    }



}