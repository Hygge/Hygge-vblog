package com.hygge.vblog.common.exception;

import lombok.Getter;

/**
 * @Classname HyggeException
 * @Description TODO    自定义运行异常
 * @Version 1.0.0
 * @Date 2022/4/17 20:29
 * @Created by hygge
 */
@Getter
public class HyggeException extends RuntimeException{

    private Integer code;
    private String msg;

    public  HyggeException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
