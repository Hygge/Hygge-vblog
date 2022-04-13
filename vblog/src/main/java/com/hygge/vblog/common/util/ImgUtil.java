package com.hygge.vblog.common.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.hygge.vblog.common.dto.BingImgsDTO;
import com.hygge.vblog.domain.BingWallpaper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/8 11:00
 * @description TODO 图片工具类
 */
@Slf4j
@Component
public class ImgUtil {

    @Value("${img.bing}")
    private String bing;
    @Value("${img.api}")
    private String api;


    /**
     * 模拟http请求，最新壁纸地址
     * @return
     */
    public String getWallpaper(){
        try {
            HttpResponse execute = HttpRequest.get(api).execute();
            BingImgsDTO bingImgsDTO = JSONUtil.toBean(execute.body(), BingImgsDTO.class);
            BingWallpaper bingWallpaper = bingImgsDTO.getImages().get(0);
            return bing + bingWallpaper.getUrl();
        }catch (Exception e){
            log.error("壁纸获取失败" + e.getMessage());
            return "e";
        }


    }


}
