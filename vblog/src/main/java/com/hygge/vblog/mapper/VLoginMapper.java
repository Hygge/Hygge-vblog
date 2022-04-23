package com.hygge.vblog.mapper;

import com.hygge.vblog.domain.VLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author hygge
* @description 针对表【v_login(用户登录记录表)】的数据库操作Mapper
* @createDate 2022-04-18 21:52:44
* @Entity com.hygge.vblog.domain.VLogin
*/
@Mapper
public interface VLoginMapper extends BaseMapper<VLogin> {

}




