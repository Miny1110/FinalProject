package com.exe.cozy;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home(Principal principal) {
		if(principal==null) {
			return"index";
		}
		System.out.println(principal.getName());
		return "index";
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
