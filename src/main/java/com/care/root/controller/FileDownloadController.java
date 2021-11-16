package com.care.root.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.FileService;

@Controller
public class FileDownloadController {
	
	@GetMapping("download")
	public void download(@RequestParam("file") String fileName, HttpServletResponse response) throws Exception {
						//file이라는 이름으로 넘어오는 값을 fileName에 대입, 동일할 땐 ("file") 생략 가능
		
		response.addHeader("Content-disposition","attachment; fileName="+fileName); 
		//응답할 때 ①Content-disposition:파일 다운로드 의미 ②attachment:파일을 다운로드하여 브라우저로 표현
		
		File file = new File(FileService.IMAGE_REPO+"/"+fileName);
		FileInputStream in = new FileInputStream(file);	//저장소에 있는 값을 가져오기 위한 inputstream
		FileCopyUtils.copy(in, response.getOutputStream());	//왼쪽에 있는 데이터를 오른쪽에 있는 데이터로 복사
		in.close();
	}
}
