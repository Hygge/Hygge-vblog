package com.hygge.vblog.common.exception;


import com.hygge.vblog.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/29 15:55
 * @description TODO    全局异常处理配置
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理方法参数异常问题
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体类校验异常：----{}", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.no(400, objectError.getDefaultMessage());
    }

    /**
     * 参数异常异常
     * @param illegalArgumentException
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException illegalArgumentException){
        log.error("Assert异常：------{}", illegalArgumentException);
        return Result.no(illegalArgumentException.getMessage());
    }

    /**
     * 授权登录异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public Result handler(ExpiredCredentialsException e){
        log.error("授权登录异常：----{}", e.getMessage());
        return Result.no(e.getMessage());
    }
    /**
     * shiro异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e){
        log.error("运行时异常：------{}", e.getMessage());
        return Result.no(401,"token已失效，请重新登录");
    }
    /**
     * 运行时异常
     * @param runtimeException
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException runtimeException){
        log.error("运行时异常：------{}", runtimeException);
        return Result.no(runtimeException.getMessage());
    }

    @ExceptionHandler(HyggeException.class)
    public Result unauthorizedExceptionHandler(HyggeException e){
        log.error("HyggeException Message :{}",e.getMessage());
        return Result.no(e.getCode(), e.getMsg());
    }

}
