package com.hygge.vblog.mapper;

import com.hygge.vblog.domain.OtherLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author hygge
* @description 针对表【other_log(日志表)】的数据库操作Mapper
* @createDate 2022-04-21 22:42:42
* @Entity com.hygge.vblog.domain.OtherLog
*/
@Mapper
public interface OtherLogMapper extends BaseMapper<OtherLog> {

}




