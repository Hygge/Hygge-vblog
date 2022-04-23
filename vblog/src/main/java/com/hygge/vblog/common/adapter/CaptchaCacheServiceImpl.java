package com.hygge.vblog.common.adapter;

import com.anji.captcha.service.CaptchaCacheService;
import com.hygge.vblog.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname CaptchaCacheServiceImpl
 * @Description TODO    验证码缓存redis
 * @Version 1.0.0
 * @Date 2022/4/17 13:26
 * @Created by hygge
 */

public class CaptchaCacheServiceImpl implements CaptchaCacheService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void set(String s, String s1, long l) {
        redisUtil.set(s, s1, l);
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public String get(String s) {
        return null;
    }

    @Override
    public String type() {
        return "redis";
    }
}
