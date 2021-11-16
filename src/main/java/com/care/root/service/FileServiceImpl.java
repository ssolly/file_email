package com.care.root.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.ShoesDTO;
import com.care.root.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired FileMapper fm;
	
	@Override
	public void fileProcess(MultipartHttpServletRequest mul) {
	
		ShoesDTO dto = new ShoesDTO();	//db에 추가를 위한
		dto.setId(mul.getParameter("id"));
		dto.setName(mul.getParameter("name"));
		
		MultipartFile file = mul.getFile("file");
		
		//동일한 이름을 저장하게 될 수도 있으니 시간을 이름에 추가해 저장(유일한 값이 필요)
		if(file.getSize() !=0 ) {	// !file.isEmpty() : 파일이 비어있지 않으면 실행
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar calendar = Calendar.getInstance();	//현재 시간을 가지고 있는 calendar
			String sysFileName = format.format(calendar.getTime());	//지정한 형태(yyyyMMddHHmmss-)로 추가
			sysFileName += file.getOriginalFilename();	//최종 저장되는 파일 이름
			
			dto.setImgName(sysFileName);	//만약 파일이 있다면 dto(db)에 파일이름을 저장
			
			File saveFile = new File(IMAGE_REPO + "/" + sysFileName);
			
			try {
				file.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			dto.setImgName("nan");
		}
		fm.saveData(dto);
	}

	@Override
	public void getShoesImage(Model model) {
		model.addAttribute("list",fm.getShoesImage());
	}
	
}
