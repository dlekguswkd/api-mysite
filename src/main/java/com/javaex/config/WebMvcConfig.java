package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**") 			// 경로 (api로 오는 주소만 해주겠다)
				.allowedMethods("GET", "POST", "PUT", "DELETE")		// 이 4개다 허락해주겠다
				.allowedOrigins("http://localhost:3000","http://localhost:9000")	// 여기에서 오는것만 허락해주겠다
				.allowedHeaders("*") // 모든 요청해더
				.exposedHeaders("Authorization")//노출시킬헤더
				.allowCredentials(true); // 쿠키허용
	}
	
	
	// 사진(첨부파일 올리기)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// 파일저장경로 변수
		String saveDir;
		String osName = System.getProperty("os.name").toLowerCase();
		
		if(osName.contains("linux")) {
			System.out.println("리눅스");
//			saveDir = "/home/ec2-user/upload/";
			saveDir = "/app/upload/";
			
		}else {
			System.out.println("윈도우");
			saveDir = "C:\\javaStudy\\upload\\";
		}
		
		registry.addResourceHandler("/upload/**")	// 여기주소를 치면 **은 저장된사진이름
		// http://localhost:9000/upload/17274050789417b18e6f7-2f94-4f8e-a2ce-b635812f58b3.jpg
				.addResourceLocations("file:" + saveDir); 	
									// 사진경로 여기에 있다
	}
	
}