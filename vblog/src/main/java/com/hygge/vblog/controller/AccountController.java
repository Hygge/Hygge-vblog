package com.hygge.vblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hygge.vblog.common.annotation.OtherLog;
import com.hygge.vblog.common.dto.LoginDto;
import com.hygge.vblog.common.dto.RegisterDTO;
import com.hygge.vblog.common.emu.Constants;
import com.hygge.vblog.common.exception.HyggeException;
import com.hygge.vblog.common.result.Result;
import com.hygge.vblog.common.util.ImgUtil;
import com.hygge.vblog.common.util.JwtUtil;
import com.hygge.vblog.common.util.RedisUtil;
import com.hygge.vblog.common.vo.UserVo;
import com.hygge.vblog.config.HyggeConfig;
import com.hygge.vblog.domain.VLogin;
import com.hygge.vblog.domain.VUser;
import com.hygge.vblog.service.VLoginService;
import com.hygge.vblog.service.VUserService;
import com.hygge.vblog.shiro.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;

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
    private VUserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HyggeConfig hyggeConfig;
    @Autowired
    private ImgUtil imgUtil;
    //        文档链接  https://blog.csdn.net/weixin_43247803/article/details/113666136
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private VLoginService loginService;


    /**
     * 登录
     *
     * @param loginDto 登录账号密码
     * @param response
     * @return
     */
    @OtherLog(logName = "登录")
    @PostMapping("/login")
    public Result login(@Valid@RequestBody LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) {
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
        response.setHeader(hyggeConfig.getHeader(), jwt);
        response.setHeader("Access-control-Expose-Headers", hyggeConfig.getHeader());
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo, "password", "createdDate");
        //插入登录记录
        saveLogin(request, user);
        return Result.ok(userVo);
    }

    /**
     * 保存登录记录
     * @param request
     * @param user
     */
    private void saveLogin(HttpServletRequest request, VUser user){
        String ip = request.getRemoteHost();
        String url = hyggeConfig.getIpApi() + ip + hyggeConfig.getCn();
        String s = HttpUtil.get(url);
        log.info(">>>>>>>>>>正在查询登录地址>>>>>>>>>>");
        VLogin login = new VLogin();
        login.setIp(ip);
        login.setEmail(user.getEmail());
        login.setCreateDate(new Date());
        login.setAddress(s);
        loginService.newSave(login);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @OtherLog(logName = "退出登录")
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.ok(200, "已退出登录");
    }

    /**
     * 首次注册博客账号
     *
     * @param registerDTO
     * @return
     */
    @OtherLog(logName = "注册账号")
    @PostMapping("/register")
    public Result Register(@RequestBody RegisterDTO registerDTO) {
        // 检查数据库有没有user，有代表注册过了，不能注册了
        List<VUser> list = userService.list();
        if (list.size() > 0){
            throw new HyggeException(424, "你已经注册过了");
        }
        VUser user = new VUser();
        user.setUserName(registerDTO.getUserName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setAvator("https://pic4.zhimg.com/v2-8195dfd64bea7800b33c47b943509ed7_r.jpg");
        return Result.ok(200, "注册成功");
    }







    /**
     * 更新个人信息
     *
     * @param userVo
     * @return
     */
    @OtherLog(logName = "修改个人信息")
    @RequiresAuthentication
    @PostMapping("/upUserInfo")
    public Result upUserInfo(@RequestBody UserVo userVo) {
        AccountProfile principal = (AccountProfile)SecurityUtils.getSubject().getPrincipal();
        Integer id = principal.getId();
        VUser byId = userService.getById(id);
        BeanUtil.copyProperties(userVo, byId, "coverImgUrl");
        byId.setId(id);
        userService.updateById(byId);
        return Result.ok(userVo);
    }

    /**
     * 重置密码
     * @return
     */
    @PostMapping("/resetPassword")
    public Result resetPasword(String code, String password){
        if (StringUtils.isBlank(code) || StringUtils.isBlank(password)){
            throw new HyggeException(424, "验证码或密码不能为空");
        }
        VUser byId = userService.getById(1L);
        String code1 = (String) redisUtil.get(Constants.EMAIL.getKey() + byId.getEmail());
        if (!code1.equals(code)){
            throw new HyggeException(424, "验证码错误");
        }
        byId.setPassword(password);
        userService.updateById(byId);
        return Result.ok("修改成功");
    }

}
