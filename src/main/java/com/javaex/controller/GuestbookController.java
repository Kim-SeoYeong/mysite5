package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/guestbook")
public class GuestbookController {
	//필드
	@Autowired
	private GuestbookService guestbookService;
	
	//방명록 리스트
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String guestList(Model model) {
		System.out.println("/guestbook/list");
		
		List<GuestVo> guestbookList = guestbookService.guestList();
		//guestbookList를 모델에 담아줌.
		model.addAttribute("gList", guestbookList);
		
		return "/guestbook/addList";
	}
	//http://localhost:8088/mysite5/guestbook/list?name=박서영&password=1111&content=방명록입니다
	//방명록 등록
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public String guestbookInsert(@ModelAttribute GuestVo guestVo) {
		System.out.println("/guestbook/insert");
		
		guestbookService.guestbookInsert(guestVo);
		
		return "redirect:/guestbook/list";
	}
	
	//방명록 삭제 폼
	@RequestMapping(value="/deleteForm", method={RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("/guestbook/deleteForm");
		
		return "/guestbook/deleteForm";
	}
	
	//http://localhost:8088/mysite5/guestbook/delete?password=1111&no=5
	//방명록 삭제
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String guestDelete(@ModelAttribute GuestVo guestVo) {
		System.out.println("/guestbook/delete");
		
		int count = guestbookService.guestDelete(guestVo);
		
		//이부분은 컨트롤러말고 서비스쪽에서 처리해줘야함. 
		if(count == 1) {
			return "redirect:/guestbook/list";
		} else {
			
			return "redirect:/guestbook/deleteForm?result=fail&no=" + guestVo.getNo();
		}

	}
}
