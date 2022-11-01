package com.exe.cozy.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.PointDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.service.CustomerService;
import com.exe.cozy.service.PointService;
import com.exe.cozy.service.ReplyService;
import com.exe.cozy.util.AddDate;
import com.exe.cozy.util.CreatePoint;

@Controller
public class ReplyController {

	@Resource
	private ReplyService replyService;
	@Resource 
	private CustomerService customerService;
	
	@Resource 
	private PointService pointService;
	
	@Autowired
	AddDate addDate;
	
	@Autowired 
	CreatePoint createPoint;

	//리뷰창으로 이동
	@GetMapping("/reviewWrite")
	public ModelAndView insertReply(int itemNum) throws Exception {

		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewWrite");
		mav.addObject("itemNum",itemNum);
		return mav;

	}
	//리뷰쓰기
	@PostMapping("/reviewWrite_ok")
    public ModelAndView reviewWrite_ok(@ModelAttribute CustomerDto dto,ReplyDto rdto, HttpServletRequest request,Principal principal) throws Exception{

        ModelAndView mav = new ModelAndView();
        int replyMaxNum =replyService.replyMaxNum();
        rdto.setReplyId(replyMaxNum + 1);
       
       

        
        //리뷰쓰기 테스트용 아이디
        rdto.setCustomerEmail(principal.getName());
       
      
        
       //point 테이블에 데이터 넣기
       pointService.insertData(createPoint.reviewPoint(principal.getName()));
       replyService.insertReply(rdto);

        mav.setViewName("redirect:/");
        return mav;
    }
	//리뷰수정
	@PostMapping("updateReview")
    public ModelAndView updateReply(@ModelAttribute ReplyDto rdto) throws Exception {
		
		ModelAndView mav = new ModelAndView();
    	replyService.updateReply(rdto);
				
		return mav;
		
	}

}
