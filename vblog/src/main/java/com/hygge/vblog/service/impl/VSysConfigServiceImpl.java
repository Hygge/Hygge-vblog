package com.hygge.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hygge.vblog.domain.VSysConfig;
import com.hygge.vblog.service.VSysConfigService;
import com.hygge.vblog.mapper.VSysConfigMapper;
import org.springframework.stereotype.Service;

/**
* @author hygge
* @description 针对表【v_sys_config(系统配置)】的数据库操作Service实现
* @createDate 2022-04-21 22:43:26
*/
@Service
public class VSysConfigServiceImpl extends ServiceImpl<VSysConfigMapper, VSysConfig>
    implements VSysConfigService{

}




