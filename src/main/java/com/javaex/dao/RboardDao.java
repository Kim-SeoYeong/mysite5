package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	@Autowired
	private SqlSession sqlSession;
	
	//댓글 게시판 리스트
	public List<RboardVo> rboardList() {
		System.out.println("RboardDao rboardList()");
		
		return sqlSession.selectList("rboard.selectList");
	}
	
	//댓글 조회수
	public void rboardHitUpdate(int no) {
		System.out.println("RboardDao rboardHitUpdate()");
		
		sqlSession.update("rboard.hitUpdate", no);
	}
	
	//댓글 게시글 한개 읽어오기
	public RboardVo rboardSelectOne(int no) {
		System.out.println("RboardDao rboardSelectOne()");
		
		return sqlSession.selectOne("rboard.selectOne", no);
	}
	
	//댓글게시판 글쓰기
	public void rboardInsert(RboardVo rboardVo) {
		System.out.println("RboardDao rboardInsert()");

		sqlSession.insert("rboard.insert", rboardVo);
	}
	
	//댓글게시판 댓글쓰기
	public void commentInsert(RboardVo rboardVo) {
		System.out.println("RboardDao commentInsert()");
		
		sqlSession.insert("rboard.commentInsert", rboardVo);
	}
	
	//댓글에 또 댓글 달때 order_no 증가시키는 로직
	public void orderNoUpdate(RboardVo rboardVo) {
		System.out.println("RboardDao orderNoUpdate()");
		
		sqlSession.update("rboard.orderNoUpdate", rboardVo);
	}
	
	//게시글 수정
	public void rboardUpdate(RboardVo rboardVo) {
		System.out.println("RboardDao rboardUpdate()");
		
		sqlSession.update("rboard.rboardUpdate", rboardVo);
	}
}
