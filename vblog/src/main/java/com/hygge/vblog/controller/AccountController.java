package com.hygge.vblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.anji.captcha.service.impl.BlockPuzzleCaptchaServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hygge.vblog.common.dto.LoginDto;
import com.hygge.vblog.common.dto.RegisterBTO;
import com.hygge.vblog.common.exception.HyggeException;
import com.hygge.vblog.common.result.Result;
import com.hygge.vblog.common.util.ImgUtil;
import com.hygge.vblog.common.util.JwtUtil;
import com.hygge.vblog.common.util.OSSUtil;
import com.hygge.vblog.common.util.RedisUtil;
import com.hygge.vblog.common.vo.UserVo;
import com.hygge.vblog.domain.VUser;
import com.hygge.vblog.service.VUserService;
import com.hygge.vblog.shiro.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/5 20:23
 * @description TODO
 */
@Slf4j
@RestController
public class AccountController {

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

    @Autowired
    private CaptchaService captchaService;


    /**
     * 登录
     *
     * @param loginDto 登录账号密码
     * @param response
     * @return
     */
    @PostMapping("/login")
    public Result login(@Valid@RequestBody LoginDto loginDto, HttpServletResponse response) {
        // 登陆后台登录需要再校验一遍验证码
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(loginDto.getCaptchaVerification());
        ResponseModel responseModel = captchaService.verification(captchaVO);
        if (!responseModel.isSuccess()) {
            return Result.no("验证码错误");
        }


        QueryWrapper<VUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", loginDto.getUserName()).or().eq("email", loginDto.getUserName());
        VUser user = userService.getOne(queryWrapper);
        Assert.notNull(user, "用户名不存在");
        //MD5--32位小写
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.no("密码不正确");
        }
        String jwt = jwtUtil.createJWT(String.valueOf(user.getId()));
        response.setHeader(header, jwt);
        response.setHeader("Access-control-Expose-Headers", header);
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo, "password", "createdDate");
        return Result.ok(userVo);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.ok(200, "已退出登录");
    }

    /**
     * 首次注册博客账号
     *
     * @param registerBTO
     * @return
     */
    @PostMapping("/register")
    public Result Register(@RequestBody RegisterBTO registerBTO) {
        // 检查数据库有没有user，有代表注册过了，不能注册了


        return Result.ok(200, "注册成功");
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
     * 更新个人信息
     *
     * @param userVo
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/upUserInfo")
    public Result upUserInfo(@RequestBody UserVo userVo) {
        AccountProfile principal = (AccountProfile)SecurityUtils.getSubject().getPrincipal();
        Integer id = principal.getId();
        VUser byId = userService.getById(id);
        BeanUtil.copyProperties(userVo, byId, "coverImgUrl");
        userService.update(byId, new QueryWrapper<VUser>().eq("id", byId.getId()));
        return Result.ok(userVo);
    }

}
