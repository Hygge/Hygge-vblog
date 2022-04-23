package com.hygge.vblog.common.emu;

import lombok.Getter;

/**
 * @Classname HygType
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/4/23 16:42
 * @Author hygge
 */

public enum HygType {

    IMG_TYPE(0, "图片"), LOCAL(0,"本地"), CLOUD(1,"云");

    private Integer type;

    private String desc;

    HygType (Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }
    public Integer type(){
        return type;
    }

}
