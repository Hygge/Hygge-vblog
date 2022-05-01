package com.hygge.vblog.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hygge.vblog.domain.VFileRecord;
import lombok.Data;

import java.util.List;

/**
 * @Classname ImgFileVo
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/5/1 13:07
 * @Author hygge
 */
@Data
public class ImgFileVo {

    private IPage<VFileRecord> fileRecords;
    private List<String> fileTypes;

}
