package com.example.start.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StartConfig implements WebMvcConfigurer {
    @Value("${web.upload.path}")
    private String fileUploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/upload/images/**").addResourceLocations("classpath:/upload/images/");
        registry.addResourceHandler("/upload/images/**").addResourceLocations("file:" + fileUploadPath);
    }
}
