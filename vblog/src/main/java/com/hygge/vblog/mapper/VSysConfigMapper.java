package com.hygge.vblog.mapper;

import com.hygge.vblog.domain.VSysConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author hygge
* @description 针对表【v_sys_config(系统配置)】的数据库操作Mapper
* @createDate 2022-04-21 22:43:26
* @Entity com.hygge.vblog.domain.VSysConfig
*/

@Mapper
public interface VSysConfigMapper extends BaseMapper<VSysConfig> {

}




