package com.exe.cozy.customer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource
	private CustomerService customerService;
    
    @RequestMapping(value = "/emailChk", method = RequestMethod.POST )
    @ResponseBody
    public int nameCheck(@RequestParam("email") String email) {
    	int cnt = customerService.emailChk(email);
		return cnt;
	}
    
    @GetMapping("signUp")
    public ModelAndView signUp() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("sign-up");
    	
    	return mav;
    }
    
    @PostMapping("signUp")
    public ModelAndView signUp(
			@ModelAttribute CustomerDto dto, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	customerService.insertData(dto);
    	
    	mav.setViewName("redirect:login");
    	return mav;
    	
    }
    
    @GetMapping("login")
    public ModelAndView login() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("log-in");
    	
    	return mav;
    	
    }
    
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

}
