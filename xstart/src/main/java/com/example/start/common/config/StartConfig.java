package com.example.start.common.config;

import com.example.start.chat.handler.HttpHandler;
import com.example.start.common.interceptor.SecurityInterceptor;
import com.example.start.common.servlet.CheckNumberImgServlet;
import com.example.start.common.servlet.CheckOtherImgServlet;
import com.example.start.common.servlet.CheckWordsImgServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StartConfig implements WebMvcConfigurer {
    @Value("${web.upload.path}")
    private String fileUploadPath;


    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(securityInterceptor())
//                .excludePathPatterns("/alogin")
//                .excludePathPatterns("/error").addPathPatterns("/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/images/**").addResourceLocations("file:" + fileUploadPath);
        registry.addResourceHandler("/c/**").addResourceLocations(HttpHandler.webroot);
    }
    @Bean
    public ServletRegistrationBean checkWordsImgBean() {
        return new ServletRegistrationBean(new CheckWordsImgServlet(), "/words/images");
    }
    @Bean
    public ServletRegistrationBean checkNumberImgBean() {
        return new ServletRegistrationBean(new CheckNumberImgServlet(), "/number/images");
    }
    @Bean
    public ServletRegistrationBean checkOtherImgBean() {
        return new ServletRegistrationBean(new CheckOtherImgServlet(), "/other/images");
    }

}
