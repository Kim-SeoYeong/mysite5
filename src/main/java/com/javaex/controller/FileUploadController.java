package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;

@Controller
@RequestMapping(value = "/fileUp")
class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	//파일 등록 폼
	@RequestMapping(value = "/form", method = {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("[FileUploadController.form()]");
		return "/fileUpload/form";
	}
	
	//파일 업로드
	@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("[FileUploadController.upload()]");
		//System.out.println(file.getOriginalFilename());	//원래 파일의 이름을 알려줌. 제대로 찍히면 파일이 제대로 온 것.
		//System.out.println(file.getSize());	//사진크기
		
		String saveName = fileUploadService.restore(file);
		model.addAttribute("saveName", saveName);
		
		return "/fileUpload/result";
	}

}
