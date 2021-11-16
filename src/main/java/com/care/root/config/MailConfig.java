package com.care.root.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Bean	//Autowired로 자동 객체 생성
	public static JavaMailSender mailSender() {	//메일을 보낼 때 사용하는 계정 세팅
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		jms.setHost("smtp.gmail.com");	//google smtp 메일 서버 설정
		jms.setPort(587);				//google smtp 메일 포트
		jms.setUsername("email@address");	//메일
		jms.setPassword("passw0rd!");		//패스워드

		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");	//프로토콜(규약) : smtp(이메일)
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");	//보안처리
		prop.setProperty("mail.debug", "true");		//문제/성공 시 출력
		jms.setJavaMailProperties(prop);

		return jms;
	}
}
