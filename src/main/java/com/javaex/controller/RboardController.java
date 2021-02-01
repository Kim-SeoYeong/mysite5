package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/rboard")
public class RboardController {
	
	@Autowired
	private RboardService rBoardService;
	
	//댓글 게시판 리스트
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String rboardList(Model model) {
		System.out.println("/rboard/list");
		
		List<RboardVo> rboardList = rBoardService.rboardList();
		model.addAttribute("rbList", rboardList);
		
		return "/rboard/rboardList";
	}

	//댓글 읽기
	@RequestMapping(value="/read", method={RequestMethod.GET, RequestMethod.POST})
	public String rboardRead(@RequestParam("no") int no, Model model) {
		System.out.println("/rboard/read");
		
		RboardVo rboardVo = rBoardService.rboardRead(no);
		
		model.addAttribute("rbVo", rboardVo);
		
		return "/rboard/rboardRead";
	}
	
	//댓글게시판 글쓰기 폼
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String rboardWriteForm() {
		System.out.println("/rboard/writeForm/");
		
		return "/rboard/rboardWriteForm";
	}
	
	//댓글게시판 글쓰기
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String rboardWrite(@ModelAttribute("rboardVo") RboardVo rboardVo, HttpSession session) {
		System.out.println("/rboard/write/");
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		rboardVo.setUserNo(userVo.getNo());
		rBoardService.rboardInsert(rboardVo);
		
		return "redirect:/rboard/list";
	}
	
	//댓글 쓰기 폼
	@RequestMapping(value="/commentWriteForm", method={RequestMethod.GET, RequestMethod.POST})
	public String commentForm(@RequestParam("groupNo") int groupNo, Model model) {
		System.out.println("rboard/commentWriteForm");
		
		RboardVo rBoardVo = rBoardService.rboardReadOne(groupNo);
		model.addAttribute("rbVo", rBoardVo);
		
		return "/rboard/rboardWriteForm";
	}

	//댓글게시판 글수정 폼
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String rboardModifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("/rboard/modifyForm/");
		
		RboardVo rBoardVo = rBoardService.rboardReadOne(no);
		model.addAttribute("rbVo", rBoardVo);
		
		return "/rboard/rboardModifyForm";
	}
	
	//댓글게시판 글 수정
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String rboardModify(@ModelAttribute("rBoardVo") RboardVo rBoardVo) {
		System.out.println("/rboard/modify");
		
		rBoardService.rboardUpdate(rBoardVo);
		
		return "redirect:/rboard/list";
	}
}
