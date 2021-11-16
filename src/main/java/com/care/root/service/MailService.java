package com.care.root.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired JavaMailSender mailSender;	//설정된 값들이 저장
	
	public void sendMail(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();		//보조 도구와 같은 역할 : 전송 준비 단계
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");	//true:image나 multipart형식도 허용하겠다	
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);	//true:html형식으로 전달 가능
			
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	   public void auth(HttpServletRequest request) {
		      HttpSession session = request.getSession();
		      String userid = "idid";
		      String userkey = rand();										//랜덤한 키 생성
		      session.setAttribute(userid, userkey);
		      String body="<h2>안녕하세요 아뱅입니다</h2><hr>"						//사용자 email로 넘어갈 값
		            +"<h3>"+userid+" 님</h3>"
		            +"<p>인증하기 버튼을 누르면 로그인 됩니다</p>"
		            +"<a href='http://localhost:8888"
		            +request.getContextPath()+"/auth_check?userid="			//절대경로
		            +userid+"&userkey="+userkey+"'>인증하기</a>";
		      sendMail("silver_white@naver.com","이메일 인증입니다",body);		//받는 사용자
		   }
		   private String rand() {
		      Random ran = new Random();
		      String str="";
		      int num;
		      while(str.length() != 20) {
		         num = ran.nextInt(75)+48;
		         if((num>=48 && num<=57)||(num>=65 && num<=90)||(num>=97 && num<=122)) {
		            str+=(char)num;
		         }else {
		            continue;
		         }
		      }
		      return str;
		   }
}
