package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private HttpSession session;
	
	//게시판 리스트 폼
	public List<BoardVo> boardList() {
		System.out.println("BoardService boardList()");
		List<BoardVo> boardList = boardDao.boardList();
		//System.out.println(boardList.toString());
		
		return boardList;
	}
	
	//게시글 읽기 (조회수 처리까지 한번에 해주자)
	public BoardVo boardRead(int no) {
		System.out.println("BaordService boardRead()");
		boardDao.hitUpdate(no);
		
		return boardDao.boardSelectOne(no);
	}
	/*
	//조회수 업데이트
	public void hitUpdate(int no) {
		System.out.println("BaordService hitUpdate()");
		
		boardDao.hitUpdate(no);
	}
	*/
	//게시글 수정 폼
	public BoardVo boardSelectOne(int no) {
		System.out.println("BoardService boardSelectOne()");
		
		return boardDao.boardSelectOne(no);
	}
	//게시 수정
	public void boardUpdate(BoardVo boardVO) {
		System.out.println("BoardService boardUpdate()");
		
		boardDao.boardUpdate(boardVO);
	}
	
	//게시글 삭제
	public void boardDelete(int no) {
		System.out.println("BoardService boardDelete()");
		
		boardDao.boardDelete(no);
	}

	//게시글 쓰기 
	public void boardInsert(BoardVo boardVo) {
		System.out.println("BoardService boardInsert()");
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(userVo.getNo());
		boardDao.boardInsert(boardVo);
		
	}
}
