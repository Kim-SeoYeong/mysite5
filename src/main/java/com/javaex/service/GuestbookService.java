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
	
	//ajax 글 저장	--> 저장된 글 리턴
	public GuestVo writeResultVo(GuestVo guestVo) {
		System.out.println("[GuestbookService] writeResultVo()");
		//방금저장한 글 조회하기
		//기존의 insert문의 문제 --> 글번호가 몇번인지 모르니까 가장 최근 글을 가져온다.(문제가있음, 저장하고 최근글이 방금 작성한 글이라는 보장이없음.)
		
		//위의 문제를 해결할 수 있는 로직
		//no값을 알 수 있다.
		//int no = guestbookDao.insertSelectKey(guestVo);
		System.out.println("service : dao.insertSelectKey() 실행전 ==> " + guestVo);
		guestbookDao.insertSelectKey(guestVo);
		System.out.println("service : dao.insertSelectKey() 실행후 ==> " + guestVo);
		int no = guestVo.getNo();
		
		//글 1개 가져오기
		return guestbookDao.selectOne(no);
	}

	
	
}
