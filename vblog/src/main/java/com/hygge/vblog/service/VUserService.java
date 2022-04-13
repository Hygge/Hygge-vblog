package com.hygge.vblog.service;

import com.hygge.vblog.domain.VUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface VUserService extends IService<VUser> {


    /**
     * 发送邮箱验证码
     */
    void sendEmailCode(String email, String subject, String fromEamil);

}
