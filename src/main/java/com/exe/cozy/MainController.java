package com.exe.cozy;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.service.CustomerService;

@Controller
public class MainController {
	
	@Autowired CustomerService customerService;

	@RequestMapping("/")
	public ModelAndView home(Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		if(principal==null) {
			mav.setViewName("index");
			return mav;
		}
		
		String customerName = customerService.getReadData(principal.getName()).getCustomerName();
		
		mav.addObject("customerName", customerName);
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping("/category")
	public String category() {
		return "shopCategory";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "log-in";
	}
	


}
