package com.exe.cozy.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.NoticeDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.domain.ServiceQuestionDto;
import com.exe.cozy.service.ServiceQuestionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Controller
public class ServiceQnacontroller {
	
	@Resource
	private ServiceQuestionService svcQueService;
	
	
	//질문 등록창
	@GetMapping("/createSvcQue")
	public ModelAndView createSvcQuestion(String customerEmail) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("serviceQnaCreate");
		mav.addObject("customerEmail",customerEmail);
		
		return mav;
	}
	
	
	//질문 등록
	@PostMapping("/createSvcQue_ok")
	public ModelAndView createSvcQuestion_ok(ServiceQuestionDto sqdto, HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		int serviceQueMaxNum = svcQueService.serviceQueMaxNum();
		sqdto.setServiceQueNum(serviceQueMaxNum + 1);
		
		sqdto.setCustomerEmail("lej3999@naver.com");
		
		svcQueService.insertServiceQue(sqdto);;
		
		
		mav.setViewName("redirect:/serviceQnaList");
		return mav;
		
	}
	
	//리스트 띄우기
	@PreAuthorize("isAuthenticated")
	@RequestMapping("/listSvcQue")
	public ModelAndView listSvcQuestion(NoticeDto ndto, HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
				
		String quePageNumStr = request.getParameter("pageNum");
		
		if(quePageNumStr==null) {
			quePageNumStr = "1";
    	}
		
		int pageNum = Integer.parseInt(quePageNumStr);
		
		Page<ServiceQuestionDto> sqlists = svcQueService.getServiceQuePaging(pageNum);
		
		PageInfo<ServiceQuestionDto> page = new PageInfo<>(sqlists,3);
		
		mav.addObject("sqlists",sqlists);
		mav.addObject("page",page);
		
		mav.setViewName("serviceQnaList");
		
		return mav;
	}
	
	
	/*
	//질문 수정창
	@GetMapping("updateSvcQuestion")
	public ModelAndView updateSvcQue(ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception{
		
		int customerEmail = Integer.parseInt(request.getParameter("customerEmail"));
		
		sqdto = svcQueService.findServiceQue
		ModelAndView mav = new ModelAndView();
		
		svcQueService.updateServiceQue(sqdto);
		mav.setViewName("redirect:/");
		return mav;
	}
	
	//질문 등록 수정
	@PostMapping("updateSvcQuestion_ok")
	public ModelAndView updateSvcQue_ok(ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception{
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		
		svcQueService.updateServiceQue(sqdto);
		mav.setViewName("redirect:/");
		return mav;
	}
		
		
	
	//질문 답변창으로 이동
	@GetMapping("createSvcAns")
	public ModelAndView
	
	
	
	
	
	
	*/
	
	
}
