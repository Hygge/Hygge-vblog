package com.hygge.vblog.mapper;

import com.hygge.vblog.domain.VFileRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author hygge
* @description 针对表【v_file_record(文件存储记录表)】的数据库操作Mapper
* @createDate 2022-04-21 22:43:06
* @Entity com.hygge.vblog.domain.VFileRecord
*/

@Mapper
public interface VFileRecordMapper extends BaseMapper<VFileRecord> {

}




