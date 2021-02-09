package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	//전체리스트 가져오기(ajax)
	@ResponseBody	//resopnse의 body에다가 데이터를 넣어줘. .jsp로 안넘기고 data로 넘길거야. 라고 알려주는부분. 제이슨으로 보내줌
	@RequestMapping(value = "/list")
	public List<GuestVo> list() {
		System.out.println("[apiGuestbookController] / list");
		
		return guestbookService.guestList();
	}

	//글작성(ajax)
	@ResponseBody
	@RequestMapping(value = "/write")
	public GuestVo write(@ModelAttribute GuestVo guestVo) {
		System.out.println("[apiGuestbookController] / write");
		System.out.println(guestVo);
		
		//입력된 vo값을 저장시키고 나중에 저장된 vo를 받아야함.
		return guestbookService.writeResultVo(guestVo);

	}

}
