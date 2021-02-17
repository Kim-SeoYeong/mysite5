package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	@Autowired
	private HttpSession session;
	
	//갤러리 리스트
	public List<GalleryVo> galleryList2() {
		System.out.println("[GalleryService.galleryList2()]");
		
		List<GalleryVo> galList = galleryDao.gallerySelectList2();
		//System.out.println(galList);
		
		return galList;
	}
	
	public Map<String, Object> galleryList(int crtPage) {
		System.out.println("[GalleryService.galleryList()]");
		
		//페이징
		
		//페이지당 글 갯수
		int listCnt = 12;
		
		//현재 페이지
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);  //조건 ? 참 : else
		
		//시작글 번호 startRNum
		//1page --> 1 	2Page-->13	 3page --> 25
		int startRNum = (crtPage-1) * listCnt + 1;
		
		//끝글 번호 endRNum
		//1page(1,12)	2page(13,24)	3page(25,36)
		int endRNum = (startRNum + listCnt) - 1;
		
		List<GalleryVo> galleryList = galleryDao.gallerySelectList(startRNum, endRNum);
		
		//System.out.println(galleryList);
		
		//페이징 계산
		
		//페이지당 버튼 갯수
		int pageBtnCount = 10;
		
		//전체 글 갯수 구하기
		int totalcount = galleryDao.galleryTotalCount();
		//System.out.println(totalcount);
		
		//마지막 버튼 번호
		//10/10.0=1.0 1*10=10	, 11/10.0=1.1 20 
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount -1);
		
		//다음버튼 boolean
		boolean next;
		
		if(endPageBtnNo * listCnt < totalcount) {	//10*12 < 121
			next = true;
		} else {	//10*12 < 119
			next = false;
		}
		
		//이전 버튼 boolean
		boolean prev;
		if(startPageBtnNo != 1) {
			prev = true;
		} else {
			prev = false;
		}
		
		Map<String, Object> gMap = new HashMap<String, Object>();
		gMap.put("galleryList", galleryList);
		gMap.put("prev", prev);
		gMap.put("startPageBtnNo", startPageBtnNo);
		gMap.put("endPageBtnNo", endPageBtnNo);
		gMap.put("next", next);
		
		return gMap;
	}
	
	//갤러리 이미지 등록
	public void galleryUpload(GalleryVo galleryVo, MultipartFile file) {
		System.out.println("[GalleryService.galleryUpload()]");
		
		//작성자 이름을 가져오기 위해 세션에서 no값을 가져오자
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		galleryVo.setUserNo(userVo.getNo());
		
		//DB파일 정보 수집
		//파일을 저장할 경로
		String saveDir = "C:\\javaStudy\\upload";
		//System.out.println("saveDir = " + saveDir);
		
		//원본 파일 이름
		String orgName = file.getOriginalFilename();
		//System.out.println("orgName = " + orgName);
		galleryVo.setOrgName(orgName);
		
		//파일 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		//System.out.println("exName = " + exName);
		
		//서버저장 파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		//System.out.println("saveName = " + saveName);
		galleryVo.setSaveName(saveName);
		
		//서버 파일패스 --> 저장경로(실제로 하드디스크 어디에 저장되있는지)
		String filePath = saveDir + "\\" + saveName;
		//System.out.println("filePath = " + filePath);
		galleryVo.setFilePath(filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		//System.out.println("fileSize = " + fileSize);
		galleryVo.setFileSize(fileSize);
		
		//서버 하드에 파일저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bous = new BufferedOutputStream(out);
			
			bous.write(fileData);
			bous.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(galleryVo.toString());
		
		galleryDao.galleryInsert(galleryVo);
	}
	
	
	//갤러리 글 하나만 조회하기
	public GalleryVo gallerySelectOne(int no) {
		System.out.println("[GalleryService.gallerySelectOne()]");
		
		return galleryDao.selectOne(no);
	}
	
	//갤러리 글 삭제하기
	public int galleryDelete(int no) {
		System.out.println("[GalleryService.galleryDelete()]");
		
		return galleryDao.galleryDelete(no);
	}
}
