package com.exe.cozy.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/service/qnaCreate")
	public ModelAndView createSvcQuestion(String customerEmail) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("serviceQnaCreate");
		mav.addObject("customerEmail",customerEmail);
		
		return mav;
	}
	
	
	//질문 등록
	@PreAuthorize("isAuthenticated")
	@PostMapping("/service/qnaCreate_ok")
	public ModelAndView createSvcQuestion_ok(ServiceQuestionDto sqdto, HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		int serviceQueMaxNum = svcQueService.serviceQueMaxNum();
		sqdto.setServiceQueNum(serviceQueMaxNum + 1);
		
		sqdto.setCustomerEmail("lej3999@naver.com");
		
		svcQueService.insertServiceQue(sqdto);;
		mav.setViewName("redirect:/service/qnaList");
		return mav;
		
	}
	
	//질문 리스트 띄우기
	@PreAuthorize("isAuthenticated")
	@RequestMapping("/service/qnaList")
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
	
	
	//질문 내용 보이기
	@GetMapping("/service/qnaArticle")
	public ModelAndView articleSvcQuestion(ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception {
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		sqdto = svcQueService.findServiceQue(serviceQueNum);
		mav.addObject("sqdto",sqdto);
		mav.setViewName("serviceQna_Adm");
		
		return mav;
	}
	
	
	
	//질문 수정창
	@GetMapping("/service/qnaUpdate")
	public ModelAndView updateSvcQuestion(@ModelAttribute ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception{
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		sqdto = svcQueService.findServiceQue(serviceQueNum);
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("serviceQnaUpdate");
		mav.addObject("ServiceQuestionDto",sqdto);
		
		return mav;
	}
	
	//질문 등록 수정
	@PostMapping("/service/qnaUpdate_ok")
	public ModelAndView updateSvcQuestion_ok(@ModelAttribute ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		svcQueService.updateServiceQue(sqdto);
		mav.setViewName("redirect:/service/qnaList");
		
		return mav;
	}
		
	//질문 삭제
	@GetMapping("deleteQuestion")
	public ModelAndView deleteSvcQuestion(ServiceQuestionDto sqdto, HttpServletRequest request) throws Exception {
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));

		ModelAndView mav = new ModelAndView();
		svcQueService.deleteServiceQue(serviceQueNum);
		mav.setViewName("redirect:/service/qnaList");
		return mav;
	}
	
	
	
	/*
	
	//질문 답변창으로 이동
	@GetMapping("createSvcAns")
	public ModelAndView
	
	
	
	
	
	
	*/
	
	
}
