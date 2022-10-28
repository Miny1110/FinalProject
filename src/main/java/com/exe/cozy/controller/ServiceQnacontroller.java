package com.exe.cozy.controller;


import java.security.Principal;

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
import com.exe.cozy.domain.ServiceAnswerDto;
import com.exe.cozy.domain.ServiceQuestionDto;
import com.exe.cozy.service.ServiceAnswerService;
import com.exe.cozy.service.ServiceQuestionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Controller
public class ServiceQnacontroller {
	
	@Resource
	private ServiceQuestionService svcQueService;
	
	@Resource
	private ServiceAnswerService svcAnsService;
	
	
	//질문 등록창
	@GetMapping("/service/qnaCreate")
	public ModelAndView createSvcQuestion(String customerEmail, Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("serviceQnaCreate");
		mav.addObject("principal",principal);
		
		return mav;
	}
	
	
	//질문 등록
	//@PreAuthorize("isAuthenticated")
	@PostMapping("/service/qnaCreate_ok")
	public ModelAndView createSvcQuestion_ok(ServiceQuestionDto sqdto,Principal principal, HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		int serviceQueMaxNum = svcQueService.serviceQueMaxNum();
		sqdto.setServiceQueNum(serviceQueMaxNum + 1);
		
		sqdto.setCustomerEmail(principal.getName());
		
		svcQueService.insertServiceQue(sqdto);;
		mav.setViewName("redirect:/service/qnaList");
		return mav;
		
	}
	
	//질문 리스트 띄우기
	//@PreAuthorize("isAuthenticated")
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
	
	//질문 내용 보이기 (관리자 답변창 보이기)
	@GetMapping("/service/qnaArticle")
	public ModelAndView articleSvcQuestion(ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception {
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		sqdto = svcQueService.findServiceQue(serviceQueNum);
		mav.addObject("sqdto",sqdto);
		mav.setViewName("serviceQna_Cus");
		
		return mav;
	}
	

	
	//질문 수정창
	@GetMapping("/service/qnaUpdate")
	public ModelAndView qnaUpdate(@ModelAttribute ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception{
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		sqdto = svcQueService.findServiceQue(serviceQueNum);
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("serviceQnaUpdate");
		mav.addObject("ServiceQuestionDto",sqdto);
		
		return mav;
	}
	
	
	//질문 수정
	@PostMapping("/service/qnaUpdate_ok")
	public ModelAndView qnaUpdate_ok(@ModelAttribute ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		svcQueService.updateServiceQue(sqdto);
		mav.setViewName("redirect:/service/qnaList");
		
		return mav;
	}
		
	//질문 삭제
	@GetMapping("deleteQuestion")
	public ModelAndView deleteSvcQuestiosn(ServiceQuestionDto sqdto, HttpServletRequest request) throws Exception {
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));

		ModelAndView mav = new ModelAndView();
		svcQueService.deleteServiceQue(serviceQueNum);
		mav.setViewName("redirect:/service/qnaList");
		return mav;
	}
	
	
	//--------------------------------
	
	//질문 리스트 띄우기
		//@PreAuthorize("isAuthenticated")
		@RequestMapping("/service/anqList")
		public ModelAndView listSvcAnswer(NoticeDto ndto, HttpServletRequest request) throws Exception{
			
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
			mav.setViewName("serviceAnqList");
			
			return mav;
		}
	
	
	
	
	//관리자 질문 답변창으로 이동
	@GetMapping("/service/anqUpdate")
	public ModelAndView updateSvcAnswer(ServiceAnswerDto sadto, HttpServletRequest request)throws Exception {
		
		int serviceAnsNum = Integer.parseInt(request.getParameter("serviceAnsNum"));
		
		ModelAndView mav = new ModelAndView();
		sadto = svcAnsService.findServiceAns(serviceAnsNum);
		mav.addObject("sadto", sadto);
		mav.setViewName("serviceQna_Adm");
		
		return mav;
	}
	
	
	
	//답변 등록
	@PostMapping("/service/anqCreate_ok")
	public ModelAndView anqCreate_ok(@ModelAttribute ServiceAnswerDto sadto, HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		int serviceAnsMaxNum = svcAnsService.serviceAnsMaxNum();
		sadto.setServiceAnsNum(serviceAnsMaxNum + 1);
		System.out.println(sadto.getServiceAnsContent());
		
		
		svcAnsService.insertServiceAns(sadto);
		System.out.println(sadto.getServiceAnsNum());
		mav.setViewName("redirect:/service/qnaList");
		
		
		return mav;
		
	}
	
	
	
	//답변 내용 보이기
	@GetMapping("/service/anqArticle")
	public ModelAndView articleSvcAnswer(ServiceQuestionDto sqdto,ServiceAnswerDto sadto,HttpServletRequest request) throws Exception {
		int serviceAnsNum = Integer.parseInt(request.getParameter("serviceAnsNum"));
		
		ModelAndView mav = new ModelAndView();
		sadto = svcAnsService.findServiceAns(serviceAnsNum);
		mav.addObject("sadto",sadto);
		mav.setViewName("serviceQnaView_Adm");
		
		return mav;
	}
	
	//답변 수정
	@PostMapping("/service/anqUpdate_ok")
	public ModelAndView updateSvcAnswer_ok(ServiceAnswerDto sadto, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();

		svcAnsService.updateServiceAns(sadto);
		// System.out.println(sadto.getRating());
		// System.out.println(sadto.getContent());
		mav.setViewName("redirect:/");
		return mav;
	}
	
	
	//답변 삭제
	@GetMapping("deleteAnswer")
	public ModelAndView deleteReply(ServiceAnswerDto sadto, HttpServletRequest request) throws Exception {
		int serviceAnsNum = Integer.parseInt(request.getParameter("replyId"));

		ModelAndView mav = new ModelAndView();
		svcAnsService.deleteServiceAns(serviceAnsNum);
		mav.setViewName("redirect:/");
		return mav;
	}
	
	
}
