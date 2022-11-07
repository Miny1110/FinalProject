package com.exe.cozy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.MailDto;
import com.exe.cozy.mail.MailService;
import com.exe.cozy.service.CustomerService;
import com.exe.cozy.service.PointService;
import com.exe.cozy.util.CreatePoint;
import com.exe.cozy.util.CustomerChk;

@Controller
public class LogController {
	
	@Resource private CustomerService customerService;
	@Resource private PointService pointService;
	@Resource private MailService mailService;
	
	@Autowired CustomerChk customerChk;
	@Autowired CreatePoint createPoint;

	//이메일 중복확인
    @RequestMapping(value = "/emailChk", method = RequestMethod.POST )
    @ResponseBody
    public int nameCheck(@RequestParam("email") String email) {
    	int cnt = customerService.emailChk(email);
		return cnt;
	}
    
    //회원가입 화면
    @GetMapping("signUp")
    public ModelAndView signUp() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("sign-up");
    	
    	return mav;
    }
    
    //회원가입
    @PostMapping("signUp")
    public ModelAndView signUp(@ModelAttribute CustomerDto dto, BindingResult bindingResult, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	if(bindingResult.hasErrors()) {
    		mav.setViewName("sign-up");
    		return mav;
    	}
    	
    	//customer 테이블에 데이터 넣기
    	customerService.insertData(dto);
    	
    	//point 테이블에 데이터 넣기
    	pointService.insertData(createPoint.signUpPoint(dto.getCustomerEmail()));
    	
    	//로그인 화면으로 이동
    	mav.setViewName("redirect:login");
    	return mav;
    	
    }
    
    //로그인 화면
    @GetMapping("login")
    public ModelAndView login() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("log-in");
    	
    	return mav;
    	
    }
    
    //비밀번호찾기 화면
    @GetMapping("forgot")
    public ModelAndView forgot() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("forgot");
    	
    	return mav;
    }
    
    //비밀번호찾기
    @PostMapping("forgot")
    public ModelAndView forgot(String customerEmail, String customerTel, HttpServletRequest req, RedirectAttributes rattr) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	boolean check = customerChk.forgotCheck(customerEmail, customerTel);
    	
    	if(!check) {
    		rattr.addFlashAttribute("msg", "회원정보가 없습니다.");
    		
    		mav.setViewName("redirect:forgot");
    		
    		return mav;
    	}

    	//임시비밀번호 발급
    	String customerPwd = customerChk.getTmpPwd();
    	
    	//이메일발송
    	MailDto mailDto = mailService.createMail(customerPwd, customerEmail);
    	mailService.sendMail(mailDto);
    	
    	//임시비밀번호 저장
    	CustomerDto dto = new CustomerDto();
    	dto.setCustomerEmail(customerEmail);
    	dto.setCustomerPwd(customerPwd);
    	
    	customerService.updatePwd(dto);
    	
    	mav.setViewName("redirect:sendEmail");
    	
    	return mav;
    }
    
    
    //비밀번호찾기 이메일전송 화면
    @GetMapping("sendEmail")
    public ModelAndView sendEmail() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("sendEmail");
    	
    	return mav;
    }    
    
    
    
//-------------------------------------------------------------------
    @GetMapping("stateUp")
    public ModelAndView stateUp(HttpServletRequest req) {
    	ModelAndView mav = new ModelAndView();
    	String orderNum = req.getParameter("orderNum");
    	customerService.stateUp(orderNum);
    	mav.setViewName("redirect:/");
    	return mav;
    }
    
}
