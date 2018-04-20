package com.example.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

@SpringBootApplication
@MapperScan("com.example.start.mapper")
public class XstartApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(XstartApplication.class, args);
	}

	@Bean
	Filter characterEncodingFilter(){
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}
}
