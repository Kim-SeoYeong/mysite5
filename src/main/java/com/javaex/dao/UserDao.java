package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	//회원가입 --> 회원정보 저장
	public int insert(UserVo userVo) {
		System.out.println("user dao insert");
		System.out.println(userVo.toString());

		return sqlSession.insert("user.insert", userVo);
	}
	
	//로그인 --> 회원정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("user dao selectUser");
		System.out.println(userVo.toString());
		//UserVo vo = sqlSession.selectOne("user.selectUser", userVo);
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
	//회원정보 조회해오기
	public UserVo selectOne(int no) {
		System.out.println("user dao selectOne");
		//System.out.println(no);	//no값 잘 가져오나 테스트해보자
		
		return sqlSession.selectOne("user.selectOne", no);
	}
	
	public void update(UserVo userVo) {
		System.out.println("user dao update");
		//System.out.println("dao 업데이트" + userVo.toString());
		sqlSession.update("user.update", userVo);
	}
}
