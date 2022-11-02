package com.exe.cozy.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.FaqDto;
import com.exe.cozy.service.FaqService;


@Controller
public class FaqController {

	@Resource
	private FaqService faqService;

	
	//faq 입력창 띄우기
	@GetMapping("/faq/create") 
	public ModelAndView faqCreate() throws Exception {
	 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("faqCreate");
		return mav;
	
	}
	
	//faq 등록
	@PostMapping("/faq/create_ok")
	public ModelAndView faqCreate_ok(FaqDto fdto,HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		int MaxFaqNum = faqService.maxFaqNum();
		fdto.setFaqNum(MaxFaqNum + 1);
		
		faqService.insertFaqData(fdto);
		
		mav.setViewName("redirect:/faq/list");
		return mav;
	}
	
	
	//faq 리스트 띄우기
	@RequestMapping("/faq/list")
	public ModelAndView listFaq(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		List<FaqDto> flists = faqService.getReadFaqList();
		
		
		List<FaqDto> flist1 = faqService.getFaqType1();
		List<FaqDto> flist2 = faqService.getFaqType2();
		List<FaqDto> flist3 = faqService.getFaqType3();
		
		
		mav.addObject("flists",flists);
		mav.addObject("flist1",flist1);
		mav.addObject("flist2",flist2);
		mav.addObject("flist3",flist3);
		
		mav.setViewName("faqList");
		return mav;
	}
	
	
	
	//update
	@GetMapping("/faq/update")
	public ModelAndView faqUpdate(FaqDto fdto,HttpServletRequest request) throws Exception{
		
		int faqNum = Integer.parseInt(request.getParameter("faqNum"));
		
		fdto = faqService.getReadFaqData(faqNum);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("faqUpdate");
		mav.addObject("fdto",fdto);
		
		return mav;
	}
	
	
	
	@PostMapping("/faq/update_ok")
	public ModelAndView faqUpdate_ok(FaqDto fdto, HttpServletRequest request) throws Exception{
		
		int faqNum = Integer.parseInt(request.getParameter("faqNum"));
		
		ModelAndView mav = new ModelAndView();
		faqService.updateFaqData(fdto);
		
		System.out.println(fdto.getFaqContent());
		mav.setViewName("redirect:/faq/list");
		
		return mav;
	}
	
	/*
	@GetMapping("/noticeDelete")
	public ModelAndView delete_ok(HttpServletRequest request) throws Exception{
		
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		ModelAndView mav = new ModelAndView();
		noticeService.deleteNoticeData(noticeNum);
		mav.setViewName("redirect:/notice/list");
		
		return mav;
		
	}
	*/
	
}
