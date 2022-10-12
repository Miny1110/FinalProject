package com.exe.cozy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	
	
	@RequestMapping("/main")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "log-in";
	}
	


}
