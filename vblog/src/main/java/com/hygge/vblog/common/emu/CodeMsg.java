package com.hygge.vblog.common.emu;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/29 13:24
 * @description TODO    状态枚举类
 */

public enum CodeMsg {

    /**
     * 200正常，400异常
     */
    SUCCESS(200, "OK！"),ERROR(424, "fail!");


    private Integer code;
    private String msg;

    CodeMsg() {
    }

    CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
