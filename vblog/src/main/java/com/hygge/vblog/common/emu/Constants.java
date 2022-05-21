package com.hygge.vblog.common.emu;

import lombok.Getter;

/**
 * @Classname Constants
 * @Description TODO 关键字
 * @Version 1.0.0
 * @Date 2022/4/23 14:24
 * @Author hygge
 */
@Getter
public enum Constants {

    RESOURCE_PREFIX("/upload", "静态资源文件的前缀"),SUFFIX("suffix","文件后缀名"),
    FILE_NAME("fileName", "文件名"), NEW_FILE_NAME("newFileName", "新文件名"),
    PATH_FILE_NAME("pathFileName", "文件路径名"), PROFILE("profile","路径"),
    TYPE("type","Integer类型值"),LOCAL_OR_CLOUD("localOrCloud","本地文件还是云存储"),
    EMAIL("email:", "邮箱缓存前缀");

    private String key;
    private String word;

    Constants (String key, String word){
        this.key = key;
        this.word = word;
    }

}
