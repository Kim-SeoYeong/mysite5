package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestbookDao {
	@Autowired
	private SqlSession sqlSession;
	
	//방명록 리스트
	public List<GuestVo> guestList() {
		System.out.println("GuestbookDao guestList()");
		
		return sqlSession.selectList("guestbook.selectList");
	}
	
	//방명록 작성
	public void guestbookInsert(GuestVo guestVo) {
		System.out.println("GuestbookDao guestbookInsert()");
		//System.out.println(guestVo.toString());
		sqlSession.insert("guestbook.insert", guestVo);
	}
	
	//방명록 삭제
	public int guestDelete(GuestVo guestVo) {
		System.out.println("GuestbookDao guestDelete()");
		
		int count = sqlSession.delete("guestbook.delete", guestVo);
		return count;
	}
	
	//글저장(selectKey)
	public int insertSelectKey(GuestVo guestVo) {
		System.out.println("[guestbookDao] insertSelectKey()");
		
		System.out.println("dao : xml 실행전 ==> " + guestVo);
		sqlSession.insert("guestbook.insertSelectKey", guestVo);
		System.out.println("dao : xml 실행후 ==> " + guestVo);
		return guestVo.getNo();
	}
	
	//글 1개 가져오기
	public GuestVo selectOne(int no) {
		System.out.println("[guestbookDao] selectOne()");
		return sqlSession.selectOne("guestbook.select", no);
	}

}
