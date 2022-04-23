package com.hygge.vblog.service;

import com.hygge.vblog.domain.VLogin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hygge
* @description 针对表【v_login(用户登录记录表)】的数据库操作Service
* @createDate 2022-04-18 21:52:44
*/
public interface VLoginService extends IService<VLogin> {

    /**
     * 保存登录记录
     * @param login
     */
    void newSave(VLogin login);

}
