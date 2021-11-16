package com.care.root.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration	//설정을 하는 값, 이 안에 bean을 설정(xml에서 만들던 방식 -> annotation을 활용한 java코드로 만드는 방식)
public class FileConfig {

	@Bean //메소드 실행 후 return 값을 bean으로 만들어줌
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxUploadSize(52428800); //50MB
		mr.setDefaultEncoding("utf-8");
		return mr;
	}
}
