package com.hygge.vblog.common.aspectJ;

import cn.hutool.json.JSONUtil;
import com.hygge.vblog.domain.OtherLog;
import com.hygge.vblog.service.OtherLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/3/19 14:12
 * @description TODO 日志切面处理
 */

@Slf4j
@Aspect
@Component
public class OtherLogAop extends DefaultAdvisorAutoProxyCreator {

    @Autowired
    private OtherLogService otherLogService;

    // 由于自定义切面代理与shiro注解冲突 采用明确告诉使用默认创建 DefaultAdvisorAutoProxyCreator
    @Override
    public boolean isUsePrefix() {
        return true;
    }

    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.hygge.vblog.common.annotation.OtherLog)")
    public void operationLog(){}

    /**
     * 环绕通知记录日志通过注解匹配到需要增加日志功能的方法
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("operationLog()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        // 1.方法执行前的处理，相当于前置通知
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取类名
        String class_name = pjp.getTarget().getClass().getName();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取请求参数
        Object[] args = pjp.getArgs();
//        String[] params1 = Arrays.stream(args).toArray(String[]::new);
        String params = JSONUtil.toJsonStr(args);
        log.info(params);
        // 获取方法上面的注解
        com.hygge.vblog.common.annotation.OtherLog otherLogAop = method.getAnnotation(com.hygge.vblog.common.annotation.OtherLog.class);
        // 获取操作描述的属性值
        String name = otherLogAop.logName();
        // 创建一个日志对象(准备记录日志)
        OtherLog log = new OtherLog();
        log.setName(name);
        log.setType(otherLogAop.value());// 操作说明
        log.setMothod(method.getName());
        log.setClassName(class_name);
        log.setParams(params);
        log.setUserId(1);
        log.setIp("");
        log.setRemarks("");
        Object result = null;
        try {
            // 让代理方法执行
            result = pjp.proceed();
            // 2.相当于后置通知(方法成功执行之后走这里)
            log.setSucceed("成功");   // 设置操作结果
        } catch (SQLException e) {
            // 3.相当于异常通知部分
            log.setSucceed("失败");   // 设置操作结果
        } finally {
            // 4.相当于最终通知
//            log.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 设置操作日期
            log.setCreateTime(new  Date()); // 设置操作日期
            // 打印日志记录
            System.out.println(log.toString());
            // 在此处直接调用service插入数据库也可以。
            otherLogService.save(log);
            // 也可以学习guns一样利用线程池进行日志存储。
        }
        return result;
    }


    @Before("operationLog()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        System.out.println("进入方法前执行.....");

    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "operationLog()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("operationLog()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("operationLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }


}
