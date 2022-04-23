package com.hygge.vblog.common.annotation;

import java.lang.annotation.*;

/**
 * @Classname OtherLog
 * @Description TODO    系统日志注解
 * @Version 1.0.0
 * @Date 2022/4/21 22:07
 * @Author hygge
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OtherLog {
    String logName();//日志名称
    String value() default "非业务类";
}
