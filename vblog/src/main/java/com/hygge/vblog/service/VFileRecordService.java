package com.hygge.vblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hygge.vblog.common.dto.ImgDTO;
import com.hygge.vblog.domain.VFileRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
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

    /**
     * 分页获取文件
     * @param imgDTO
     * @return
     */
    IPage<VFileRecord> getAllImg(ImgDTO imgDTO);

    /**
     * 获取所有文件后缀
     * @return
     */
    List<String> getSuffixList();

}
