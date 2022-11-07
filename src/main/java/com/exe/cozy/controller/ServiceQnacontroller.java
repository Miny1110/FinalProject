package com.exe.cozy.controller;


import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		
		
		mav.setViewName("questionCreate");
		mav.addObject("customerEmail",principal.getName());
		
		return mav;
	}
	
	
	//질문 등록
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
	@RequestMapping("/service/qnaList")
	public ModelAndView listSvcQuestion(ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception{
		
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
		
		mav.setViewName("questionList");
		
		return mav;
	}
	
	
	//질문 내용 보이기
	@GetMapping("/service/qnaArticle")
	public ModelAndView articleSvcQuestion(ServiceQuestionDto sqdto,HttpServletRequest request) throws Exception {
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		sqdto = svcQueService.findServiceQue(serviceQueNum);
		
		mav.addObject("sqdto",sqdto);
		mav.setViewName("questionArticle");
		
		return mav;
	}
	
	
	
	//질문 수정창
	@GetMapping("/service/qnaUpdate")
	public ModelAndView qnaUpdate(@ModelAttribute ServiceQuestionDto sqdto,HttpServletRequest request,Principal principal, HttpSession session) throws Exception{
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		sqdto = svcQueService.findServiceQue(serviceQueNum);
		ModelAndView mav = new ModelAndView();
		
		sqdto.setCustomerEmail(principal.getName());
		
		String customerEmail = (String)session.getAttribute("customerEmail");
        sqdto.setCustomerEmail(customerEmail);
		
		mav.setViewName("questionUpdate");
		mav.addObject("ServiceQuestionDto",sqdto);
		
		return mav;
	}
	
	
	//질문 수정
	@PostMapping("/service/qnaUpdate_ok")
	public ModelAndView qnaUpdate_ok(@ModelAttribute ServiceQuestionDto sqdto,HttpServletRequest request,Principal principal, HttpSession session) throws Exception{
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		
		sqdto.setCustomerEmail(principal.getName());
		
		svcQueService.updateServiceQue(sqdto);
		mav.setViewName("redirect:/service/qnaArticle?serviceQueNum=" + serviceQueNum);
		
		String customerEmail = (String)session.getAttribute("customerEmail");
        sqdto.setCustomerEmail(customerEmail);
		
		return mav;
	}
		
	//질문 삭제
	@GetMapping("deleteQuestion")
	public ModelAndView deleteSvcQuestiosn(ServiceQuestionDto sqdto, HttpServletRequest request,Principal principal,HttpSession session) throws Exception {
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		sqdto.setCustomerEmail(principal.getName());
		
		String customerEmail = (String)session.getAttribute("customerEmail");
        sqdto.setCustomerEmail(customerEmail);
        
		ModelAndView mav = new ModelAndView();
		svcQueService.deleteServiceQue(serviceQueNum);
		mav.setViewName("redirect:/service/qnaList");
		return mav;
	}
	
	
	//--------------------------------
	
	
	//관리자 답변 내용 보이기
	@GetMapping("/service/anqArticle")
	public ModelAndView articleSvcAnswer(ServiceQuestionDto sqdto,ServiceAnswerDto sadto,HttpServletRequest request) throws Exception {
		
		//질문 보이기
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		sqdto = svcQueService.findServiceQue(serviceQueNum);
		mav.addObject("sqdto",sqdto);
		
		
		if(sadto == null) {
			
			mav.setViewName("/service/anqArticle");
		}
		
		sadto = svcAnsService.findServiceAns(serviceQueNum);
		mav.addObject("sadto",sadto);
		
		System.out.println(sqdto.getCustomerEmail());
		
		mav.setViewName("answerArticle");
		return mav;
		
	}
	
	
	//답변 등록창으로 이동
	@GetMapping("/service/anqCreate")
	public ModelAndView createSvcAnswer(HttpServletRequest request,HttpSession session) throws Exception{
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		ServiceQuestionDto sqdto = svcQueService.findServiceQue1(serviceQueNum);
		
		String customerEmail = (String)session.getAttribute("customerEmail");
        sqdto.setCustomerEmail(sqdto.getCustomerEmail());
		
		mav.addObject("sqdto",sqdto);
		
		mav.setViewName("answerCreate");
		//mav.addObject("customerEmail",principal.getName());
		
		return mav;
	}
	
	
	//답변 등록
	@PostMapping("/service/anqCreate_ok")
	public ModelAndView anqCreate_ok(ServiceQuestionDto sqdto,ServiceAnswerDto sadto, HttpServletRequest request, HttpSession session) throws Exception{

		ModelAndView mav = new ModelAndView();
		int serviceAnsMaxNum = svcAnsService.serviceAnsMaxNum();
		sadto.setServiceAnsNum(serviceAnsMaxNum + 1);
		
		String customerEmail = (String)session.getAttribute("admin@.com");
        sqdto.setCustomerEmail(customerEmail);
		
		svcAnsService.insertServiceAns(sadto);
		int serviceQueNum = sadto.getServiceQueNum();

		mav.setViewName("redirect:/service/anqArticle?serviceQueNum=" + serviceQueNum);
		return mav;
	}
	
	
	//답변 수정창 이동
	@GetMapping("/service/anqUpdate")
	public ModelAndView anqUpdate(ServiceAnswerDto sadto,HttpServletRequest request) throws Exception{
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		ServiceQuestionDto sqdto = svcQueService.findServiceQue(serviceQueNum);
		
		mav.addObject("sqdto",sqdto);
		
		mav.setViewName("answerUpdate");
		
		return mav;
	}
	
	 
		
	//답변 수정
	@PostMapping("/service/anqUpdate_ok")
	public ModelAndView anqUpdate_ok(@ModelAttribute ServiceAnswerDto sadto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		svcAnsService.updateServiceAns(sadto);
		
		mav.setViewName("redirect:/service/qnaList");
		
		return mav;
	}
	
	
	//답변 삭제
	@GetMapping("deleteAnswer")
	public ModelAndView deleteReply(ServiceAnswerDto sadto, HttpServletRequest request) throws Exception {
		
		int serviceQueNum = Integer.parseInt(request.getParameter("serviceQueNum"));
		
		ModelAndView mav = new ModelAndView();
		svcAnsService.deleteServiceAns(serviceQueNum);
		mav.setViewName("redirect:/service/anqArticle?serviceQueNum=" + serviceQueNum);
		return mav;
	}
	
	
}
