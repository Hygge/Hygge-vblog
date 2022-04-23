package com.hygge.vblog.config;

import com.hygge.vblog.common.emu.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/29 16:30
 * @description TODO    跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Autowired
    private HyggeConfig hyggeConfig;

    /**
     * 添加跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
        registry.addResourceHandler( Constants.RESOURCE_PREFIX.getKey() + "/**")
                .addResourceLocations("file:" + hyggeConfig.getProfile() + "/");
    }
}
