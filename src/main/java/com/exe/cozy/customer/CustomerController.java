package com.exe.cozy.customer;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exe.cozy.deliver.DeliveryService;
import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.domain.MailDto;
import com.exe.cozy.domain.PointDto;
import com.exe.cozy.mail.MailService;
import com.exe.cozy.point.PointService;
import com.exe.cozy.util.AddDate;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource private CustomerService customerService;
	@Resource private PointService pointService;
	@Resource private MailService mailService;
	@Resource private DeliveryService deliveryService;
	
	@Autowired
	AddDate addDate;
    
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
    public ModelAndView signUp(
			@ModelAttribute CustomerDto dto, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	//customer 테이블에 데이터 넣기
    	customerService.insertData(dto);
    	
    	//point 테이블에 데이터 넣기
    	PointDto pointDto = new PointDto();
    	
    	int pointNum = pointService.maxNum();
    	pointDto.setPointNum(pointNum+1);
    	pointDto.setPointTitle("회원가입");
    	pointDto.setPointContent("회원가입 축하 포인트");
    	pointDto.setPointAmount(3000);
    	pointDto.setPointState("지급");
    	pointDto.setPointEndDate(addDate.addDate(30));
    	pointDto.setCustomerEmail(dto.getCustomerEmail());
    	
    	pointService.insertData(pointDto);
    	
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
    
    //로그인
    @PostMapping("login")
    public ModelAndView login(String customerEmail, String customerPwd, HttpServletRequest req, RedirectAttributes rattr) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	customerService.getLogin(customerEmail);
    	boolean check = customerService.loginCheck(customerEmail, customerPwd);

    	if(!check) { //로그인 실패
    		rattr.addFlashAttribute("msg", "이메일 또는 비밀번호가 일치하지 않습니다.");
    		
    		mav.setViewName("redirect:login");
    		
    		return mav;
    	}
    	
    	//로그인 성공 시 세션에 이메일 저장
    	HttpSession session = req.getSession();
    	
    	session.setAttribute("customerEmail", customerEmail);
    	
    	mav.setViewName("redirect:/");
    	
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
    	
    	customerService.forgot(customerEmail);
    	
    	boolean check = customerService.forgotCheck(customerEmail, customerTel);
    	
    	if(!check) {
    		rattr.addFlashAttribute("msg", "회원정보가 없습니다.");
    		
    		mav.setViewName("redirect:forgot");
    		
    		return mav;
    	}

    	//임시비밀번호 발급
    	String customerPwd = customerService.getTmpPwd();
    	
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
    
    
    //마이페이지 회원정보
    @GetMapping("info")
    public ModelAndView info(HttpSession session) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	//나중엔 필요없는 코드
    	session.setAttribute("customerEmail", "bbb@bbb.com");
    	
    	String customerEmail = (String)(session.getAttribute("customerEmail"));
    	
    	CustomerDto customerDto = customerService.getReadData(customerEmail);
    	
    	//비밀번호 *로 변환
    	String customerPwd = customerDto.getCustomerPwd();
    	int customerPwdLen = customerPwd.length();
    	String changePwd = "";
    	for(int i=0;i<customerPwdLen;i++) {
    		changePwd += "*";
    	}
    	customerDto.setCustomerPwd(changePwd);
    	
    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-info");
    	
    	return mav;
    }
    
    //마이페이지 회원정보수정
    @PostMapping("info")
    public ModelAndView info(@ModelAttribute CustomerDto dto) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	customerService.updateData(dto);
    	
    	//주소 입력했으면 Deliver 테이블에 insert
    	if(dto.getCustomerZipCode()!=null) {
    		DeliverDto ddto = new DeliverDto();
    		
    		int maxNum = deliveryService.maxNumDeliver();
    				
    		ddto.setDeliverNum(maxNum+1);
    		ddto.setCustomerEmail(dto.getCustomerEmail());
    		ddto.setDeliverName(dto.getCustomerName());
    		ddto.setDeliverRAddr(dto.getCustomerRAddr());
    		ddto.setDeliverJAddr(dto.getCustomerJAddr());
    		ddto.setDeliverDAddr(dto.getCustomerDAddr());
    		ddto.setDeliverZipCode(dto.getCustomerZipCode());
    		ddto.setDeliverTel(dto.getCustomerTel());
    		
    		deliveryService.insertDeliver(ddto);
    	}
    	
    	mav.setViewName("redirect:info");
    	
    	return mav;
    	
    }
    
    //마이페이지 배송지관리
    @GetMapping("address")
    public ModelAndView address(HttpSession session) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("mypage-address");
    	
    	return mav;
    }
    
    //마이페이지 포인트
    @GetMapping("point")
    public ModelAndView point(HttpSession session) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	String customerEmail = (String)session.getAttribute("customerEmail");

    	List<PointDto> lists = pointService.getList(customerEmail);
    	int totalPoint = pointService.getTotal(customerEmail);
    	
    	mav.addObject("lists", lists);
    	mav.addObject("totalPoint", totalPoint);
    	
    	mav.setViewName("mypage-point");
    	
    	return mav;
    }
    
    //마이페이지 회원탈퇴 화면
    @GetMapping("withdraw")
    public ModelAndView withdraw() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("mypage-withdraw");
    	
    	return mav;
    }
    
    //마이페이지 회원탈퇴 처리
    @PostMapping("withdraw")
    public ModelAndView withdraw(HttpSession session) {
    	ModelAndView mav = new ModelAndView();
    	
    	String customerEmail = (String)session.getAttribute("customerEmail");
    	
    	customerService.deleteData(customerEmail);
    	
    	mav.setViewName("redirect:/");
    	
    	return mav;
    }

}
