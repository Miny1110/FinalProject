package com.exe.cozy.itemDetail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.ReplyDto;

@Controller
public class ReplyController {

	@Resource
	private ReplyService replyService;

	@GetMapping("/reviewWrite")
	public ModelAndView insertReply(int itemNum) throws Exception {

		//System.out.println("get"+ itemNum);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewWrite");
		mav.addObject("itemNum",itemNum);
		return mav;

	}
	@PostMapping("/reviewWrite_ok")
    public ModelAndView reviewWrite_ok(ReplyDto rdto, HttpServletRequest request) throws Exception{

        ModelAndView mav = new ModelAndView();
        int replyMaxNum =replyService.replyMaxNum();
        rdto.setReplyId(replyMaxNum + 1);
        rdto.setCustomerEmail("test");

       
       replyService.insertReply(rdto);

        mav.setViewName("redirect:/");
        return mav;
    }
	@PostMapping("updateReview")
    public ModelAndView updateReply(@ModelAttribute ReplyDto rdto) throws Exception {
		
		ModelAndView mav = new ModelAndView();
    	replyService.updateReply(rdto);
				
		return mav;
		
	}

}
