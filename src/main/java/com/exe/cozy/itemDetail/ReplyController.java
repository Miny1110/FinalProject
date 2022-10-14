package com.exe.cozy.itemDetail;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReplyController {

	@Resource
	private ReplyService replyService;
	
	@GetMapping("/reivewWrite")
	public ModelAndView insertReply() throws Exception{
		
		ModelAndView mav = new ModelAndView();
        mav.setViewName("reivewWrite");
        return mav;
		
		
		
	}
	
}
