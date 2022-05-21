package com.hygge.vblog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hygge.vblog.common.annotation.OtherLog;
import com.hygge.vblog.common.dto.ImgDTO;
import com.hygge.vblog.common.emu.Constants;
import com.hygge.vblog.common.emu.HygType;
import com.hygge.vblog.common.exception.HyggeException;
import com.hygge.vblog.common.result.Result;
import com.hygge.vblog.common.util.ImgUtil;
import com.hygge.vblog.common.util.JwtUtil;
import com.hygge.vblog.common.util.OSSUtil;
import com.hygge.vblog.common.util.RedisUtil;
import com.hygge.vblog.common.util.file.FileUploadUtil;
import com.hygge.vblog.common.util.page.PagetionUtil;
import com.hygge.vblog.common.vo.ImgFileVo;
import com.hygge.vblog.config.HyggeConfig;
import com.hygge.vblog.domain.VFileRecord;
import com.hygge.vblog.domain.VUser;
import com.hygge.vblog.service.VFileRecordService;
import com.hygge.vblog.service.VUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    @Autowired
    ImgUtil imgUtil;
    //        文档链接  https://blog.csdn.net/weixin_43247803/article/details/113666136
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    HyggeConfig hyggeConfig;
    @Autowired
    VFileRecordService fileRecordService;



    @RequiresAuthentication
    @PostMapping("/listImg")
    public Result listImg(@RequestBody ImgDTO imgDTO){

        IPage<VFileRecord> allImg = fileRecordService.getAllImg(imgDTO);

        ImgFileVo fileVo = new ImgFileVo();
        fileVo.setFileRecords(allImg);
        fileVo.setFileTypes(fileRecordService.getSuffixList());
        return Result.ok(fileVo);
    }

    /**
     * 通用上传请求（单个）
     */
    @OtherLog(logName = "上传一个文件")
    @RequiresAuthentication
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) throws IOException {
            // 上传文件路径
            String filePath = hyggeConfig.getProfile();
            // 上传并返回新文件名称
            Map<String, String> map = FileUploadUtil.upload(filePath, file);
            map.put("size", String.valueOf(file.getSize()));
            map.put(Constants.PROFILE.getKey(), filePath);
            // 保存数据库
            fileRecordService.save(map);
            return Result.ok(map.get(Constants.PATH_FILE_NAME.getKey()));
    }
    /**
     * 通用上传请求（多个）
     */
    @OtherLog(logName = "批量上传文件")
    @RequiresAuthentication
    @PostMapping("/uploadList")
    public Result uploadFiles(List<MultipartFile> files) {
        if (files == null || files.isEmpty()){
            return Result.ok();
        }
        // 上传文件路径
        String filePath = hyggeConfig.getProfile();
        List<String> urls = new ArrayList<>();
        files.forEach( file -> {
            // 上传并返回新文件名称
            Map<String, String> map = null;
            try {
                map = FileUploadUtil.upload(filePath, file);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传失败："+ file.getOriginalFilename());
            }
            map.put("size", String.valueOf(file.getSize()));
            map.put(Constants.PROFILE.getKey(), filePath);
            // 保存数据库
            fileRecordService.save(map);
            urls.add(map.get(Constants.PATH_FILE_NAME.getKey()));
        });

        return Result.ok(urls);
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
    public Result uploadImage(@RequestParam(name = "multipartFile") MultipartFile upload, @RequestParam(name = "fileName") String fileName){
        if (upload.isEmpty() || upload == null) {
            return Result.no("图片不为空");
        }
        String url = ossUtil.upload(upload, hyggeConfig.getAddress(), fileName);
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
            throw new HyggeException(500,"图片不为空");
        }
        String url = ossUtil.upload(upload, hyggeConfig.getAddress(), "hyg");
        if (StringUtils.isBlank(url)) {
            throw new HyggeException(424,"图片上传失败");
        }
        log.info("上传一个图片：{}", upload.getName());
        Map<String, String> map = FileUploadUtil.extractFilename(upload, HygType.CLOUD.type());
        map.put(Constants.PATH_FILE_NAME.getKey(), url);
        fileRecordService.save(map);
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
            throw new HyggeException(424, "今日验证码次数已用尽");
        }
        //发送验证码，保存redis
        String flag = "博客H云验证码";
        userService.sendEmailCode(email, flag, hyggeConfig.getFromEmail());

        return Result.ok("发送成功");
    }
    /**
     * 获取当前用户验证码
     * @param request
     * @return
     */
    @PostMapping("/getCodes")
    public Result getCode(HttpServletRequest request) {
        VUser byId = userService.getById(1L);
        String email = byId.getEmail();
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
        userService.sendEmailCode(email, flag, hyggeConfig.getFromEmail());

        return Result.ok("发送成功");
    }


}
