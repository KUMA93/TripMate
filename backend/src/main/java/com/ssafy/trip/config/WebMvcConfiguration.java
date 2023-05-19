package com.ssafy.trip.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.trip.interceptor.LoginConfirmInterceptor;

@Configuration	// java로 bean configuration 설정하기 위한 Annotation
@MapperScan(basePackages = {"com.ssafy.**.model.dao"})	//MyBatis를 위한 Mapper
@EnableAspectJAutoProxy
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*", "http://localhost:5500", "http://localhost:8080")
				.allowedMethods("GET", "POST", "UPDATE", "DELETE")
				.maxAge(1800);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index").setViewName("index");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginConfirmInterceptor())
				.addPathPatterns("/board/**")
				.addPathPatterns("/mypage");
		
//		registry.addInterceptor(new )
	}
		
}
