package com.hygge.vblog.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hygge.vblog.common.util.MailUtil;
import com.hygge.vblog.common.util.RedisUtil;
import com.hygge.vblog.domain.VUser;
import com.hygge.vblog.service.VUserService;
import com.hygge.vblog.mapper.VUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 */
@Service
public class VUserServiceImpl extends ServiceImpl<VUserMapper, VUser> implements VUserService{

    @Autowired
    private VUserMapper userMapper;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private RedisUtil redisUtil;



    @Override
    public void sendEmailCode(String email, String subject, String fromEamil) {
        String redisKey = "email:" + email;
        String code = RandomUtil.randomString("0123456789qwertyuiopasdfghjklzxcvbnm", 6);
        try {
            mailUtil.sendSimpleMail(email, fromEamil, subject, code);
            redisUtil.set(redisKey, code, 60*1000L);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


}




