package com.exe.cozy.customer;

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

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.PointDto;
import com.exe.cozy.point.PointService;
import com.exe.cozy.util.AddDate;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource
	private CustomerService customerService;
	
	@Resource
	private PointService pointService;
	
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

    	if(!(loginCheck(customerEmail, customerPwd))) { //로그인 실패
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
    
    //로그인 아이디, 비밀번호 체크
    private boolean loginCheck(String customerEmail, String customerPwd) {
        CustomerDto dto = null;

        try {
            dto = customerService.getLogin(customerEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return dto!=null && dto.getCustomerPwd().equals(customerPwd);
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
    	
    	if(!(forgotCheck(customerEmail,customerTel))) {
    		rattr.addFlashAttribute("msg", "회원정보가 없습니다.");
    		
    		mav.setViewName("redirect:forgot");
    		
    		return mav;
    	}

    	mav.setViewName("redirect:sendEmail");
    	
    	return mav;
    }
    
    //비밀번호찾기 이메일, 연락처 체크
    private boolean forgotCheck(String customerEmail, String customerTel) {
        CustomerDto dto = null;

        try {
            dto = customerService.forgot(customerEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return dto!=null && dto.getCustomerTel().equals(customerTel);
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
    	
    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-info");
    	
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
