package com.hygge.vblog.common.result;

import com.hygge.vblog.common.emu.CodeMsg;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/29 13:17
 * @description TODO    返回结果集封装
 */

public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private LocalDateTime timestamp;
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg){
        this.code = code;
        this.msg = msg;
        this.data = null;
        this.timestamp = LocalDateTime.now();
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static Result ok(){
        return new Result(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg());
    }
    public static Result ok(Object data){
        return new Result(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), data);
    }
    public static Result ok(Integer code, String msg){
        return new Result(code, msg);
    }
    public static Result ok(Integer code, String msg, Object data){
        return new Result(code, msg, data);
    }
    public static Result no(){
        return new Result(CodeMsg.ERROR.getCode(), CodeMsg.ERROR.getMsg());
    }
    public static Result no(Integer code, String msg){
        return new Result(code, msg);
    }
    public static Result no(Integer code, String msg, Object data){
        return new Result(code, msg, data);
    }
    public static Result no(String msg){
        return new Result(CodeMsg.ERROR.getCode(), msg);
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
