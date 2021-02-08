package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	//필드
	//service로 전달하기 때문에 주석처리해줌. 필요가없다
	//@Autowired
	//private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	//생성자(디폴트 생성자라 생략되어있음)
	//g/s 생략
	
	//일반메소드
	
	//회원가입 폼
	@RequestMapping(value="/joinForm", method={RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("/user/joinForm");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/join", method={RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join");
		System.out.println(userVo.toString());
		
		int count = userService.join(userVo);

		return "/user/joinOk";
	}
	
	//로그인 폼
	@RequestMapping(value="/loginForm", method={RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		return "/user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login");
		System.out.println(userVo.toString());
		
		//UserVo authUser = userDao.selectUser(userVo);
		UserVo authUser = userService.login(userVo);
		
		//로그인 성공했을때
		if(authUser != null) {
			System.out.println("로그인 성공");
			
			session.setAttribute("authUser", authUser);
			
			return "redirect:/";
		} else {
		//실패했을때
		//로그인 폼으로 돌아감. 실패했습니다라는 메세지가 나와야함. result=fail 넘기는거
			System.out.println("로그인 실패");
			
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method={RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원정보 수정 폼
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("/user/modifyForm");
		
		//세션에 있는 authUser을 가져와 authVo에 담아줌.
		UserVo authVo = (UserVo)session.getAttribute("authUser");
		//authVo에서 getNo로 no값을 가져와 int no에 넣어준다. (로그인한 사람의 데이터를 가져오기 위해)
		int no = authVo.getNo();
		
		//UserVo userVo = userDao.selectOne(no);
		UserVo userVo = userService.modifyForm(no);
		//System.out.println("user controller : " + userVo.toString());
		
		//가져온 userVo를 model에 담아 넘겨줌
		model.addAttribute("uvo", userVo);
		
		return "/user/modifyForm";
	}
	
	//회원정보 수정
	//http://localhost:8088/mysite5/user/modify?password=1111&name=최서영&gender=male
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/modify");
		
		//System.out.println("세션no 넣기전 : " + userVo.toString());
		//세션에서 사용자 정보 가져오기
		UserVo authVo = (UserVo)session.getAttribute("authUser");
		//userVo no에 세션에있는 no를 넣어줌.
		userVo.setNo(authVo.getNo());
		//System.out.println("세션no 넣은 후: " + userVo.toString());
		
		//회원정보 수정
		//userDao.update(userVo);
		userService.modify(userVo);
		
		//수정된 이름을 다시 session에 넣어줌.
		authVo.setName(userVo.getName());
		
		return "redirect:/";
	}
	
	//회원가입 - 아이디 중복체크
	//응답의 body에다가 return의 값을 받아라.
	@ResponseBody	//return할때 값을 원래처럼 해석하지말고 response에 can이나 cant같은 데이터만 보내라 
	@RequestMapping(value="/idcheck", method={RequestMethod.GET, RequestMethod.POST})
	public String idCheck(@RequestParam("id") String id, @RequestParam("password") String password) {
		//패스워드는 원래 필요없는데 테스트를 위해 추가해준 것 뿐.
		
		System.out.println("/user/idcheck");
		System.out.println("checkid = " + id);
		System.out.println("password= " + password);
		
		//String result = userService.idCheck(id);
		//System.out.println(result);
	
		//return "redirect:/user/joinForm?result=" + result;
		//return "result";	//jsp를 찾는 문법
		//WEB-INF/views/result.jsp
		return ""; 	//data만 보내려고한다.	@ResponseBody ==> resopnse의 body 영역에다가 data만 보낸다.(return 값이 data)

	}
	
}
