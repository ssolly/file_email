package com.care.root.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.MailService;

@Controller
public class MailController {
	
	@Autowired MailService ms;
	
	@GetMapping("sendmail")
	public void sendMail(HttpServletResponse response) throws Exception {	
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h2>제품소개</h2>");
		sb.append("<a href=\"https://tv.naver.com/v/23369153\">");
		sb.append("<img src=\"https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_8340520%2F83405202037.jpg&type=sc960_832\">");
		sb.append("</a>");
		
		String msg=sb.toString();
		
		//ms.sendMail("silver_white@naver.com","제목 : 스프링으로 메일 보내기","내용 : 스프링으로 메일 보내기 실습 중입니다");
		ms.sendMail("silver_white@naver.com", "제목:광고",msg);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("메일 전송 완료");
	}
	
	@GetMapping("auth_form")
	public String authForm() {
		return "auth";
	}
	
	@GetMapping("auth")
	public String auth(HttpServletRequest request) {
		ms.auth(request);
		return "redirect:https://www.naver.com/";	//해당 e-mail로 redirect, 받는 사용자
	}
	
	@GetMapping("auth_check")
	public String authCheck(@RequestParam String userid, @RequestParam String userkey, HttpSession session) {
		String sessionKey = (String)session.getAttribute(userid);	//userid라는 key를 사용해서 userkey라는 value 값을 가져와 sessionKey에 저장 
		if(sessionKey != null) {
			if(sessionKey.equals(userkey)) {
				session.setAttribute("userid", userid);
			}
		}
		return "redirect:auth_form";
	}
}
