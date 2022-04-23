package com.hygge.vblog.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Classname HyggeConfig
 * @Description TODO    配置类 存放自定义配置
 * @Version 1.0.0
 * @Date 2022/4/23 11:17
 * @Author hygge
 */
@Getter
@Component
@Configuration
public class HyggeConfig {

    @Value("${jwt.header}")
    private String header;

    @Value("${hyg.catIp.api}")
    private String ipApi;

    @Value("${hyg.catIp.cn}")
    private String cn;

    @Value("${hyg.img.address}")
    private String address;

    @Value("${hyg.fromEmail}")
    private String fromEmail;

    @Value("${hyg.img.bing}")
    private String bing;

    @Value("${hyg.img.api}")
    private String bingApi;

    @Value("${hyg.img.profile}")
    private String profile;


}
