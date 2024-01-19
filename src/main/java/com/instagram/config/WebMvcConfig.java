package com.instagram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.instagram.common.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("/images/**")  // 웹의 이미지 패스: http://localhost/images/aaaa_1705483558718/winter-8425500_640.jpg
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);  //맥은 슬래시 두개 윈도우는 3개!, 파일의 실제위치
}
}
