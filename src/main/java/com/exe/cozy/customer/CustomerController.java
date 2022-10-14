package com.exe.cozy.customer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.CustomerDto;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource
	private CustomerService customerService;


//    @RequestMapping("product-bottom-thumbnail.html")
//    public String detail(){ return "product-bottom-thumbnail"; }
//    @RequestMapping("/cart.*")
//    public String cart(){ return "cart";}

    
    @RequestMapping("test")
    public String test(){ 
    	System.out.println(customerService.test());
    	
    	return "sign-up";
    }
    
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
    public ModelAndView signUp_ok(
			@ModelAttribute CustomerDto dto, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	System.out.println(dto.getCustomerName());
    	
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


}
