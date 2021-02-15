package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//게시판 리스트 폼
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model) {
		System.out.println("/board/list");
		List<BoardVo> boardList = boardService.boardList();
		//System.out.println(boardList.toString());	//잘가져오나
		//boardList를 model에 담아줌.
		model.addAttribute("bList", boardList);
		
		return "/board/list";
	}
	
	//게시판 리스트 폼(리스트 + 검색기능 추가)
	@RequestMapping(value="/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, Model model) {	//keyword의 값이 없으면 ""으로 주고 있으면 keyword 값으로 넘겨줘
		System.out.println("[BoardController.list2()]");
		//System.out.println("keword = " + keyword);
		
		List<BoardVo> boardList = boardService.getBoardList2(keyword);
		model.addAttribute("bList", boardList);
		
		return "board/list2";
	}
	
	//게시판 리스트폼(리스트 + 검색 + 페이징 기능 추가)
	@RequestMapping(value="/list3", method= {RequestMethod.GET, RequestMethod.POST})
	public String list3(@RequestParam(value = "keyword", required = false, defaultValue="") String keyword, 
						@RequestParam(value = "crtPage", required = false, defaultValue="1") int crtPage,
						Model model) {
		System.out.println("[BoardController.list3()]");
		//System.out.println("keyword = " + keyword);
		//System.out.println("crtPage = " + crtPage);
		
		Map<String, Object> pMap = boardService.getBoardList3(keyword, crtPage);
		System.out.println(pMap);
		
		model.addAttribute("pMap", pMap);
		
		return "board/list3";
	}
	
	//게시글 읽기
	@RequestMapping(value="/read", method={RequestMethod.GET, RequestMethod.POST})
	public String boardRead(@RequestParam("no") int no, Model model) {
		System.out.println("/board/read");
		
		//조회수 업데이트
		//boardService.hitUpdate(no);
		
		//BoardVo boardVo = boardService.boardSelectOne(no);
		BoardVo boardVo = boardService.boardRead(no);
		System.out.println(boardVo.toString());
		
		//boardVo를 model에 담아서 넘김.
		model.addAttribute("bvo", boardVo);
		
		
		return "/board/read";
	}
	
	//게시판 수정폼
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("/board/modifyForm");
		BoardVo boardVo = boardService.boardSelectOne(no);
		
		model.addAttribute("bvo", boardVo);
		
		return "/board/modifyForm";
	}
	
	//게시판 수정
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute("boardVo") BoardVo boardVo) {
		System.out.println("/board/modifyForm");
		System.out.println(boardVo.toString());
		boardService.boardUpdate(boardVo);	
		
		return "redirect:/board/list";
	}
	
	//게시판 삭제
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("/board/delete");
		
		boardService.boardDelete(no);
		return "redirect:/board/list";
	}
	
	//게시글 쓰기 폼
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("board/writeForm");
	
		return "/board/writeForm";
	}
	
	//게시글 쓰기
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute("boardVo") BoardVo boardVo) {
		System.out.println("/board/write");
		
		boardService.boardInsert(boardVo);
		return "redirect:/board/list";
	}
	
}
