package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {
	@Autowired
	private RboardDao rboardDao;
	
	//댓글 게시판 리스트
	public List<RboardVo> rboardList() {
		System.out.println("RboardService rboardList()");
		return rboardDao.rboardList();
	}
	
	//댓글 게시판 글 읽기
	public RboardVo rboardRead(int no) {
		System.out.println("RboardService rboardRead()");
		
		//조회수업데이트
		rboardDao.rboardHitUpdate(no);
	
		return rboardDao.rboardSelectOne(no);
	}
	
	//댓글게시판 글쓰기
	public void rboardInsert(RboardVo rboardVo) {
		System.out.println("RboardService rboardInsert()");
		
		//댓글쓰는 부분
		if(rboardVo.getGroupNo() > 0) {
			rboardVo.setOrderNo(rboardVo.getOrderNo()+1);
			//System.out.println(rboardVo.getOrderNo());
			rboardVo.setDepth(rboardVo.getDepth()+1);
			//System.out.println(rboardVo.getDepth());
			
			//댓글에 또 댓글을 달면 해당댓글 아래댓글 order_no가 1 증가해야됨. depth는 그대로 유지 insert 전에 업데이트쳐줘야함.
			rboardDao.orderNoUpdate(rboardVo);
			//댓글 인서트
			rboardDao.commentInsert(rboardVo);
			
		} else {
			//일반 글쓰기 부분
			rboardDao.rboardInsert(rboardVo);
		}
	}
	
	//한개 글 조회하기
	public RboardVo rboardReadOne(int no) {
		System.out.println("RboardService rboardReadOne()");
		
		return rboardDao.rboardSelectOne(no);
	}
	
	//게시글 수정하기
	public void rboardUpdate(RboardVo rBoardVo) {
		System.out.println("RboardService rboardUpdate()");
		
		rboardDao.rboardUpdate(rBoardVo);
	}
}
