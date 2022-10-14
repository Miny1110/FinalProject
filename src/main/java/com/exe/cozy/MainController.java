package com.exe.cozy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home() {
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
