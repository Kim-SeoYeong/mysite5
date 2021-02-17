package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;
	
	//갤러리 리스트
	public List<GalleryVo> gallerySelectList2() {
		System.out.println("[GalleryDao.gallerySelectList2()]");

		return sqlSession.selectList("gallery.galleryList");
	}
	
	//갤러리 리스트(페이징 추가)
	public List<GalleryVo> gallerySelectList(int startRNum, int endRNum) {
		System.out.println("[GalleryDao.gallerySelectList()]");
		
		Map<String, Object> gMap = new HashMap<String, Object>();
		
		gMap.put("startRNum", startRNum);
		gMap.put("endRNum", endRNum);

		return sqlSession.selectList("gallery.galleryList2", gMap);
	}
	
	//갤러리 리스트 전체갯수 조회
	public int galleryTotalCount() {
		System.out.println("[GalleryDao.galleryTotalCount()]");
		
		return sqlSession.selectOne("gallery.selectTotal");
	}
	
	//갤러리 이미지 등록
	public void galleryInsert(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.galleryInsert()]");
		
		//System.out.println(galleryVo);
		
		sqlSession.insert("gallery.galleryInsert", galleryVo);
	}
	
	//갤러리 글 하나만 조회하기
	public GalleryVo selectOne(int no) {
		System.out.println("[GalleryDao.selectOne()]");
		
		return sqlSession.selectOne("gallery.gallerySelectOne", no);
	}
	
	//갤러리 글 삭제하기
	public int galleryDelete(int no) {
		System.out.println("[GalleryDao.galleryDelete()]");
		
		return sqlSession.delete("gallery.galleryDelete", no);
	}
}
