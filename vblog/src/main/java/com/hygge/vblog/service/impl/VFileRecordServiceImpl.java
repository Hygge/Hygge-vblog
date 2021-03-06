package com.hygge.vblog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hygge.vblog.common.dto.ImgDTO;
import com.hygge.vblog.common.emu.Constants;
import com.hygge.vblog.common.emu.HygType;
import com.hygge.vblog.domain.VFileRecord;
import com.hygge.vblog.service.VFileRecordService;
import com.hygge.vblog.mapper.VFileRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author hygge
* @description 针对表【v_file_record(文件存储记录表)】的数据库操作Service实现
* @createDate 2022-04-21 22:43:06
*/
@Service
public class VFileRecordServiceImpl extends ServiceImpl<VFileRecordMapper, VFileRecord>
    implements VFileRecordService{

    @Autowired
    private VFileRecordMapper fileRecordMapper;

    @Override
    public void save(Map<String, String> map) {
        VFileRecord fileRecord = new VFileRecord();
        fileRecord.setFileName(map.get(Constants.FILE_NAME.getKey()));
        fileRecord.setFileUrl(map.get(Constants.PATH_FILE_NAME.getKey()));
        fileRecord.setFileSuffixName(map.get(Constants.SUFFIX.getKey()));
        fileRecord.setType(Integer.valueOf(map.get(Constants.TYPE.getKey())));
        fileRecord.setPath(map.get(Constants.PROFILE.getKey()));
        fileRecord.setLocalOrCloud(Integer.valueOf(map.get(Constants.LOCAL_OR_CLOUD.getKey())));
        fileRecord.setCreateDate(new Date());
        fileRecord.setSize(Long.valueOf(map.get("size")));
        save(fileRecord);
    }

    @Override
    public IPage<VFileRecord> getAllImg(ImgDTO imgDTO) {
        IPage<VFileRecord> fileRecordPage = new Page<>(imgDTO.getCurrent(), imgDTO.getPageSize());
        Long current =  (imgDTO.getCurrent() - 1) * imgDTO.getPageSize();
        List<VFileRecord> vFileRecords = fileRecordMapper.selectAllList(current, imgDTO.getPageSize(), imgDTO);
        fileRecordPage.setRecords(vFileRecords);
        fileRecordPage.setTotal(fileRecordMapper.countAllList(imgDTO));
        return fileRecordPage;
    }

    @Override
    public List<String> getSuffixList() {
        return fileRecordMapper.selectSuffixName();
    }
}




