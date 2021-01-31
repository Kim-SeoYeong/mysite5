package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트 폼
	public List<BoardVo> boardList() {
		System.out.println("BoardDao boardList()");
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		//System.out.println(boardList.toString());
		
		return boardList;
	}
	
	//조회수 업데이트
	public void hitUpdate(int no) {
		System.out.println("BoardDao hitUpdate()");
		
		sqlSession.update("board.hitUpdate", no);
	}

	//게시글 읽기 폼
	public BoardVo boardSelectOne(int no) {
		System.out.println("BoardDao boardSelectOne()");
		
		return sqlSession.selectOne("board.selectOne", no);
	}
	
	//게시판 수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("BoardDao boardUpdate()");
		//System.out.println(boardVo.toString());
		sqlSession.update("board.update", boardVo);
		//System.out.println(boardVo.toString());
	}
	
	//게시글 삭제
	public void boardDelete(int no) {
		System.out.println("BoardDao boardDelete()");
		sqlSession.delete("board.delete", no);
	}
	
	//게시글 작성
	public void boardInsert(BoardVo boardVo) {
		System.out.println("BoardDao boardInsert()");
		sqlSession.insert("board.insert", boardVo);
	}

}
