package com.hygge.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hygge.vblog.domain.VLogin;
import com.hygge.vblog.service.VLoginService;
import com.hygge.vblog.mapper.VLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author hygge
* @description 针对表【v_login(用户登录记录表)】的数据库操作Service实现
* @createDate 2022-04-18 21:52:44
*/
@Service
public class VLoginServiceImpl extends ServiceImpl<VLoginMapper, VLogin>
    implements VLoginService{

    @Autowired
    private VLoginMapper loginMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void newSave(VLogin login) {
        loginMapper.insert(login);
    }
}




