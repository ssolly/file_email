package com.care.root.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {

	public static final String IMAGE_REPO="C:/spring/image_repo"; //이미지 저장소 경로

	public void fileProcess(MultipartHttpServletRequest mul);
	public void getShoesImage(Model model);
	
}
