package com.care.root.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class MyScheduler {

	//패치같은거 할 때 사용多
	
	@Scheduled(cron="*/10 * * * * *")		//cron식(초 분 시 일 월 년)
	public void testSc() {
		System.out.println("10초에 한번씩 실행");
	}
	
}
