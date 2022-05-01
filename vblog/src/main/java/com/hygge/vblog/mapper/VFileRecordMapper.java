package com.hygge.vblog.mapper;

import com.hygge.vblog.common.dto.ImgDTO;
import com.hygge.vblog.domain.VFileRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author hygge
* @description 针对表【v_file_record(文件存储记录表)】的数据库操作Mapper
* @createDate 2022-04-21 22:43:06
* @Entity com.hygge.vblog.domain.VFileRecord
*/

@Mapper
public interface VFileRecordMapper extends BaseMapper<VFileRecord> {

    /**
     * 分页获取文件
     * @param current
     * @param size
     * @param fileRecord    条件
     * @return
     */
    List<VFileRecord> selectAllList(@Param("current")Long current, @Param("size")Long size, @Param("file") ImgDTO fileRecord);

    /**
     * 条件统计文件数量
     * @param fileRecord
     * @return
     */
    Integer countAllList(@Param("file")ImgDTO fileRecord);

    /**
     * 获取所有的文件后缀
     * @return
     */
    List<String> selectSuffixName();

}




