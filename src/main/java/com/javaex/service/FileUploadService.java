package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	public String restore(MultipartFile file) {
		System.out.println("[FileUploadService.restore()]");
		System.out.println(file.getOriginalFilename());
		
		//db 저장할 정보 수집///////////////////////////////
		String saveDir = "C:\\javaStudy\\upload";
		
		//오리지널 파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName : " + exName);
		
		//서버 저장파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName : " + saveName);
		
		//서버 파일패스 --> 저장경로(실제로 하드디스크 어디에 저장되있는지)
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath : " + filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize : " + fileSize);
		
		//서버 하드 디스크에 파일 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
		
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saveName;
		
	}
}
