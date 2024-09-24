package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**") 			// 경로 (api로 오는 주소만 해주겠다)
				.allowedMethods("GET", "POST", "PUT", "DELETE")		// 이 4개다 허락해주겠다
				.allowedOrigins("http://localhost:3000");	// 여기에서 오는것만 허락해주겠다
	}
	
}