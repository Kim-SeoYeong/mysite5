package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//게시판 리스트(리스트+검색)
	public List<BoardVo> getBoardList2(String keyword) {
		System.out.println("[BoardService.getBoardList2()]");
		//System.out.println("keyword = " + keyword);
		
		List<BoardVo> boardList = boardDao.selectList2(keyword);
		
		return boardList;
	}
	
	//게시판 리스트(리스트+검색+페이징)
	public Map<String,Object> getBoardList3(String keyword, int crtPage) {
		System.out.println("[BoardService.getBoardList3()]");
		//crtPage --> 시작번호, 끝번호    1 --> 1, 10		2 --> 21, 30		3 --> 31, 40
		
		///////////////////////////////////////////////
		// 리스트 구하기
		///////////////////////////////////////////////
		
		//페이지당 글 갯수
		int listCnt = 10;	//한페이지에 몇개씩 게시글이 보이게할지
		 
		//현재 페이지 //crtPage;
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);		//조건 ? 참 : else
		
		/*
		if(crtPage > 0) {
			crtPage = crtPage;
		} else {
			crtPage = 1;
		}
		*/
		
		//시작글 번호 startRNum
		//1page --> 1	2page-->11	3page-->21
		int startRNum = (crtPage - 1) * listCnt	+ 1;//0*10-->0 + 1	 1*10-->10 + 1	 2*10 -->20 + 1		3+10 -->30 + 1
		
		//끝글 번호 endRNum
		//1page-->(1,10)	2page-->(11,20)		3page-->(21,30)
		int endRNum = (startRNum + listCnt) - 1;
		
		List<BoardVo> boardList = boardDao.selectList3(keyword, startRNum, endRNum);
		
		
		///////////////////////////////////////////////
		// 페이징 계산
		///////////////////////////////////////////////
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//전체 글 갯수 구하기
		int totalCount = boardDao.selectTotalCnt(keyword);
		
		//1페이지 --> 1~5
		//2페이지 --> 1~5
		//3페이지 --> 1~5
		//4페이지 --> 1~5
		//5페이지 --> 1~5
		//6페이지 --> 6~10
		//7페이지 --> 6~10
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;	//1페이지-->1/5.0 = 0.2 -->1.0으로 올림처리되고 --> int형으로 변환해서 1이되고 --> 거기에 pageBtnCount를 곱해서 5가됨.Mati.ceil은 무조건 올림처리 하는 함수
				
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		//다음버튼 boolean
		boolean next;
		
		if (endPageBtnNo * listCnt < totalCount) {  //5*10 < 51
			next = true;
		} else {									//5*10 < 35
			next = false;
			endPageBtnNo = (int)Math.ceil(totalCount / (double)listCnt);
		}
		
		//이전버튼 boolean
		boolean prev;
		
		if (startPageBtnNo != 1) {
			prev = true;
		} else {
			prev = false;
		}
		
		//boardList, prev, startPageBtnNo, endPageBtnNo, next --> jsp에 전달(map으로 전달)
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
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
		
		for(int i=1; i<=1234; i++) {
			boardVo.setTitle(i + " 번째 글입니다.");
			boardVo.setContent(i + "번째 글 내용입니다.");
			boardVo.setUserNo(userVo.getNo());
			boardDao.boardInsert(boardVo);
		}
	}
}
