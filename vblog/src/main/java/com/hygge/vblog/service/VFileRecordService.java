package com.hygge.vblog.service;

import com.hygge.vblog.domain.VFileRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author hygge
* @description 针对表【v_file_record(文件存储记录表)】的数据库操作Service
* @createDate 2022-04-21 22:43:06
*/
public interface VFileRecordService extends IService<VFileRecord> {

    /**
     * 保存上传文件记录
     * @param map
     */
    void save(Map<String, String> map);

}
