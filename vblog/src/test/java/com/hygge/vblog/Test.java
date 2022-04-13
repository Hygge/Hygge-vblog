package com.hygge.vblog;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.hygge.vblog.common.dto.BingImgsDTO;
import com.hygge.vblog.domain.BingWallpaper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/5 20:48
 * @description TODO
 */

public class Test {
/*    public static void main(String[] args) {
        String s = SecureUtil.md5(String.valueOf(123456));
        System.out.println(s);
        HttpResponse execute = HttpRequest.get("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN").execute();
        BingImgsDTO bingImgsDTO = JSONUtil.toBean(execute.body(), BingImgsDTO.class);
        BingWallpaper bingWallpaper = bingImgsDTO.getImages().get(0);
        System.out.println(bingWallpaper.getUrl());

    }*/
public static void main(String[] args) {
    String qwertyuiopasdfghjklzxcvbnm = RandomUtil.randomString("qwertyuiopasdfghjklzxcvbnm0123456789", 6);
    System.out.println(qwertyuiopasdfghjklzxcvbnm);
    String email = "zeng164@163.com";
    String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    Pattern pattern = Pattern.compile(check);
    Matcher matcher = pattern.matcher(email);
    System.out.println(matcher.matches());
}
}
