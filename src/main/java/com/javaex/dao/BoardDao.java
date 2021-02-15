package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//게시판 리스트 폼2(글전체 가져오기(키워드))
	public List<BoardVo> selectList2(String keyword) {
		System.out.println("[BoardDao.selectList2()]");
		System.out.println("keyword = " + keyword);
		
		return sqlSession.selectList("board.selectList2", keyword);
		//System.out.println("boardList = " + boardList);
		
		//return boardList;
	}
	
	//게시판 리스트 폼3(글전체 가져오기(키워드)+페이징)
	public List<BoardVo> selectList3(String keyword, int startRNum, int endRNum) {
		System.out.println("[BoardDao.selectList3()]");
		//System.out.println("keyword = " + keyword);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startRNum", startRNum);
		map.put("endRNum", endRNum);
		System.out.println(map);
		
		return sqlSession.selectList("board.selectList3", map);
	}
	
	//전체 글 갯수 구하기
	public int selectTotalCnt(String keyword) {
		System.out.println("[BoardDao.selectTotalCnt()]");
		
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
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
