package com.exe.cozy.itemDetail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.customer.CustomerService;
import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.PointDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.point.PointService;
import com.exe.cozy.util.AddDate;

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

	@GetMapping("/reviewWrite")
	public ModelAndView insertReply(int itemNum) throws Exception {

		
		
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
       
       

        
        //리뷰쓰기 테스트용 아이디
        rdto.setCustomerEmail("rcm2008@naver.com");
       
      /*  리뷰쓰기 포인트 연동
        //---------------------------------------------------------------------
         
        //String customerEmail = rdto.getCustomerEmail();
        
        //point 테이블에 데이터 넣기
         PointDto pointDto = new PointDto();
         
         int pointNum = pointService.maxNum();
         pointDto.setPointNum(pointNum + 1);
         pointDto.setPointTitle("리뷰작성");
         pointDto.setPointContent("리뷰작성 감사 포인트");
         pointDto.setPointAmount(500);
         pointDto.setPointState("지급");
         pointDto.setPointEndDate(addDate.addDate(30));
         pointDto.setCustomerEmail(rdto.getCustomerEmail());
         
         pointService.insertData(pointDto);
         
         
         CustomerDto dto = customerService.getReadData(customerEmail);
         
         int plusPoint = dto.getCustomerPoint();
         dto.setCustomerPoint(plusPoint + 500); 
         customerService.updateData(dto);
        //---------------------------------------------------------------------------
        */ 
        
        
        
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
