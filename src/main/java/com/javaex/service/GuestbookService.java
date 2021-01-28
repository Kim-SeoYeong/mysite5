package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestbookService {
	
	//필드
	@Autowired
	private GuestbookDao guestbookDao;
	
	//방명록 리스트 
	public List<GuestVo> guestList() {
		System.out.println("GuestbookService guestList()");
		
		return guestbookDao.guestList();
	}
	
	//방명록 작성
	public void guestbookInsert(GuestVo guestVo) {
		System.out.println("GuestbookService guestbookInsert()");
		
		guestbookDao.guestbookInsert(guestVo);
	}
	
	//방명록 삭제
	public int guestDelete(GuestVo guestVo) {
		System.out.println("GuestbookService guestbookDelete()");
		
		int count = guestbookDao.guestDelete(guestVo);
		return count;
	}
}
