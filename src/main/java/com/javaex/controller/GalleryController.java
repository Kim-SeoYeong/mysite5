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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//갤러리 리스트 폼
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[GalleryController.list()]");
		
		List<GalleryVo> galList = galleryService.galleryList();
		//System.out.println(galList);
		model.addAttribute("galList", galList);
		
		return "/gallery/list";
	}
	
	//갤러리 리스트 폼(페이징 추가)
	@RequestMapping(value="/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value = "crtPage", required = false, defaultValue="1") int crtPage, 
						Model model) {
		System.out.println("[GalleryController.list2()]");
		
		Map<String, Object> gMap = galleryService.galleryList2(crtPage);
		//System.out.println(gMap);
		
		model.addAttribute("gMap", gMap);
		
		return "/gallery/list2";
	}
	
	//갤러리 이미지 등록
	@RequestMapping(value="/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(@ModelAttribute GalleryVo galleryVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[GalleryController.upload()]");
		
		galleryService.galleryUpload(galleryVo, file);
		
		return "redirect:/gallery/list";
	}
	
	//갤러리 이미지 등록
	@RequestMapping(value="/upload2", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload2(@ModelAttribute GalleryVo galleryVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[GalleryController.upload()]");
		
		galleryService.galleryUpload(galleryVo, file);
		
		return "redirect:/gallery/list2";
	}
	
	//갤러리 이미지 하나 조회
	@ResponseBody
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo galleryRead(@RequestParam("no") int no) {
		System.out.println("[GalleryController.galleryRead()]");
		//System.out.println("no : " + no);
		GalleryVo gvo = galleryService.gallerySelectOne(no);
		//System.out.println(gvo);
		
		return gvo;
	}
	
	//갤러리 삭제
	@ResponseBody
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public int galleryDelete(@RequestParam("no") int no) {
		System.out.println("[GalleryController.galleryDelete()]");
		//System.out.println("no : " + no);
		
		int count = galleryService.galleryDelete(no);
		
		return count;
	}
}
