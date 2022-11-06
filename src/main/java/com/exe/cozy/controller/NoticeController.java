package com.exe.cozy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.NoticeDto;
import com.exe.cozy.service.NoticeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;


@Controller
public class NoticeController {

	@Resource
	private NoticeService noticeService;

	//공지 입력창 띄우기
	@GetMapping("/notice/create") 
	public ModelAndView noticeCreate() throws Exception {
	 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("noticeCreate");
		return mav;
	
	}
	
	//공지 등록
	@PostMapping("/notice/create_ok")
	public ModelAndView noticeCreate_ok(NoticeDto ndto,HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		int MaxNoticeNum = noticeService.maxNoticeNum();
		ndto.setNoticeNum(MaxNoticeNum + 1);
		
		noticeService.insertNoticeData(ndto);
		
		mav.setViewName("redirect:/notice/list");
		return mav;
	}
	
	
	//공지 리스트 띄우기
	@RequestMapping("/notice/list")
	public ModelAndView listNotice(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		String pageNumStr = request.getParameter("pageNum");
		if(pageNumStr==null) {
			pageNumStr = "1";
    	}
		
		int pageNum = Integer.parseInt(pageNumStr);
		
		Page<NoticeDto> nlists = noticeService.getNoticeLists(pageNum);
		
		PageInfo<NoticeDto> page = new PageInfo<>(nlists,3);
		
		mav.addObject("nlists",nlists);
		mav.addObject("page",page);
		
		mav.setViewName("noticeList");
	
		return mav;
	}
	
	
	
	//공지 보기
	@GetMapping("/notice/article")
	public ModelAndView articleList(HttpServletRequest request) throws Exception {
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		ModelAndView mav = new ModelAndView();
		NoticeDto ndto = noticeService.getReadNoticeData(noticeNum);
		
		if(ndto == null) {
			mav.setViewName("redirect:/notice/list");
			return mav;
		}
		
		int lineSu = ndto.getNoticeContent().split("\n").length;
		
		mav.addObject("ndto", ndto);
		mav.addObject("lineSu", lineSu);
		mav.setViewName("noticeArticle");
		
		return mav;
	}
	
	
	//update
	@GetMapping("/notice/update")
	public ModelAndView noticeUpdate(@ModelAttribute NoticeDto ndto,HttpServletRequest request) throws Exception{
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		ndto = noticeService.getReadNoticeData(noticeNum);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("noticeUpdate");
		mav.addObject("ndto",ndto);
		
		return mav;
	}
	
	
	
	@PostMapping("/notice/update_ok")
	public ModelAndView noticeUpdate_ok(NoticeDto ndto, HttpServletRequest request) throws Exception{
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		ModelAndView mav = new ModelAndView();
		noticeService.updateNoticeData(ndto);
		mav.setViewName("redirect:/notice/article?noticeNum=" + noticeNum);
		
		return mav;
	}
	
	
	@GetMapping("/noticeDelete")
	public ModelAndView delete_ok(HttpServletRequest request) throws Exception{
		
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		
		ModelAndView mav = new ModelAndView();
		noticeService.deleteNoticeData(noticeNum);
		mav.setViewName("redirect:/notice/list");
		
		return mav;
		
	}
	
	
}
