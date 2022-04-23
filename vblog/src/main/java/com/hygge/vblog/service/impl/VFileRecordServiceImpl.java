package com.hygge.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hygge.vblog.domain.VFileRecord;
import com.hygge.vblog.service.VFileRecordService;
import com.hygge.vblog.mapper.VFileRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author hygge
* @description 针对表【v_file_record(文件存储记录表)】的数据库操作Service实现
* @createDate 2022-04-21 22:43:06
*/
@Service
public class VFileRecordServiceImpl extends ServiceImpl<VFileRecordMapper, VFileRecord>
    implements VFileRecordService{

}




