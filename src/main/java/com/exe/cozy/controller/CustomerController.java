package com.exe.cozy.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exe.cozy.DataNotFoundException;
import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.domain.MailDto;
import com.exe.cozy.domain.OrderDetailDto;
import com.exe.cozy.domain.OrderDto;
import com.exe.cozy.domain.PointDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.domain.ServiceAnswerDto;
import com.exe.cozy.domain.ServiceQuestionDto;
import com.exe.cozy.mail.MailService;
import com.exe.cozy.mapper.ServiceQuestionMapper;
import com.exe.cozy.service.CustomerService;
import com.exe.cozy.service.DeliveryService;
import com.exe.cozy.service.OrderService;
import com.exe.cozy.service.PointService;
import com.exe.cozy.service.ReplyService;
import com.exe.cozy.service.ServiceAnswerService;
import com.exe.cozy.service.ServiceQuestionService;
import com.exe.cozy.util.AddDate;
import com.exe.cozy.util.AlertRedirect;
import com.exe.cozy.util.CreatePoint;
import com.exe.cozy.util.CustomerChk;
import com.exe.cozy.util.DeliveryDupChk;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource private CustomerService customerService;
	@Resource private PointService pointService;
	@Resource private DeliveryService deliveryService;
	@Resource private ReplyService replyService;
	@Resource private OrderService orderService;
	@Resource private ServiceQuestionService serviceQuestionService;
	@Resource private ServiceAnswerService serviceAnswerService;
	
	@Autowired AddDate addDate;
	@Autowired CustomerChk customerChk;
	@Autowired DeliveryDupChk deliveryDupChk;
	@Autowired CreatePoint createPoint;
    
	
    
    
    //마이페이지 회원정보
    @PreAuthorize("isAuthenticated")
    @GetMapping("info")
    public ModelAndView info(Principal principal) {
    	
    	ModelAndView mav = new ModelAndView();
    	//principal.getName() : 사용자정보(사용자이메일) 읽어오기
    	CustomerDto customerDto = customerService.getReadData(principal.getName()); 

    	if(customerDto==null) {
    		throw new DataNotFoundException("사용자가 없습니다.");
    	}
    	
    	//비밀번호 *로 변환
    	String changePwd = customerChk.changePwd(customerDto.getCustomerPwd());
    	customerDto.setCustomerPwd(changePwd);
    	
    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-info");
    	
    	return mav;
    }
    
    //마이페이지 회원정보수정
    @PreAuthorize("isAuthenticated")
    @PostMapping("info")
    public ModelAndView info(@ModelAttribute CustomerDto dto) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	customerService.updateData(dto);
    	
    	if(dto.getCustomerZipCode()!=null && dto.getCustomerZipCode()!="") { //주소를 입력했으면
    		
    		String customerEmail = dto.getCustomerEmail();
    		List<DeliverDto> dList = deliveryService.listDeliver(customerEmail); //DELIVERDTO 객체들 LIST
    		
    		int typeChk = deliveryDupChk.typeChk(dList);
    		
    		if(typeChk!=0) {
    			DeliverDto ddto = deliveryDupChk.update(dto);
    			deliveryService.updateDeliver(ddto);
    		}else {
    			DeliverDto ddto = deliveryDupChk.insert(dto);
    			deliveryService.insertDeliver(ddto);
    		}
    		
    	}
 
    	mav.setViewName("redirect:info");
    	
    	return mav;
    	
    }
    
    //마이페이지 주문조회
    @PreAuthorize("isAuthenticated")
    @GetMapping("order")
    public ModelAndView order(Principal principal, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr="1";
    	}
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	CustomerDto customerDto = customerService.getReadData(principal.getName());

    	Page<OrderDto> orderList = customerService.getOrderList(principal.getName(), pageNum);
    	PageInfo<OrderDto> page = new PageInfo<>(orderList,3);
    	List<OrderDto> orderDetailList = customerService.getOrderDetailList(principal.getName());
    	
    	mav.addObject("page", page);
    	mav.addObject("customerDto", customerDto);
    	mav.addObject("orderList", orderList);
    	mav.addObject("orderDetailList", orderDetailList);
    	mav.setViewName("mypage-order");
    	
    	return mav;
    }
    
    //마이페이지에서 주문취소
    @PreAuthorize("isAuthenticated")
    @PostMapping("cancle")
    public ModelAndView orderCancle(Principal principal, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	pointService.insertDelData(createPoint.orderCanclePoint(principal.getName()));
    	orderService.updateCancleState(req.getParameter("orderNum"));
    	
    	mav.setViewName("redirect:order");
    	
    	return mav;
    }
    
    //마이페이지 주문상세조회
    @PreAuthorize("isAuthenticated")
    @GetMapping("order/detail")
    public ModelAndView orderDetail(Principal principal, HttpServletRequest req) {
    	ModelAndView mav = new ModelAndView();
    	
    	String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr="1";
    	}
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	String orderNum = req.getParameter("orderNum");
    	OrderDto odto = customerService.getOrderDetail(principal.getName(), orderNum);
    	List<OrderDetailDto> orderDetailList = customerService.getOrderDetailOne(principal.getName(), orderNum);
    	
    	mav.addObject("pageNum", pageNum);
    	mav.addObject("odto", odto);
    	mav.addObject("orderDetailList", orderDetailList);
    	mav.setViewName("invoice");
    	
    	return mav;
    }       
    
    //마이페이지 주문취소조회
    @PreAuthorize("isAuthenticated")
    @GetMapping("order/cancle")
    public ModelAndView orderCancleList(Principal principal, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr="1";
    	}
    	int pageNum = Integer.parseInt(pageNumStr);

    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	Page<OrderDto> orderList = customerService.getOrderCancleList(principal.getName(), pageNum);
    	PageInfo<OrderDto> page = new PageInfo<>(orderList,3);
    	List<OrderDetailDto> orderDetailList = customerService.getOrderCancleDetailList(principal.getName());

    	mav.addObject("orderList", orderList);
    	mav.addObject("page", page);
    	mav.addObject("orderDetailList", orderDetailList);
    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-order-cancle");
    	
    	return mav;
    }
    
    //마이페이지 주문취소상세조회
    @PreAuthorize("isAuthenticated")
    @GetMapping("order/cancle/detail")
    public ModelAndView orderCancleDetail(Principal principal, HttpServletRequest req) {
    	ModelAndView mav = new ModelAndView();
    	
    	String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr="1";
    	}
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	String orderNum = req.getParameter("orderNum");
    	OrderDto odto = customerService.getOrderDetail(principal.getName(), orderNum);
    	List<OrderDetailDto> orderDetailList = customerService.getOrderDetailOne(principal.getName(), orderNum);
    	
    	mav.addObject("pageNum", pageNum);
    	mav.addObject("odto", odto);
    	mav.addObject("orderDetailList", orderDetailList);
    	mav.setViewName("invoice-cancle");
    	
    	return mav;
    } 
    
    //마이페이지 문의답변
    @PreAuthorize("isAuthenticated")
    @GetMapping("qna")
    public ModelAndView qna(Principal principal, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr="1";
    	}
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	String searchKey = req.getParameter("searchKey");
    	if(searchKey==null) {;
    		searchKey="serviceQueTitle";
    	}
    	
    	String searchValue = req.getParameter("searchValue");
    	if(searchValue==null) {
    		searchValue="";
    	}
    	
    	Page<ServiceQuestionDto> lists = customerService.getQnaList(principal.getName(), searchKey, searchValue, pageNum);
    	PageInfo<ServiceQuestionDto> page = new PageInfo<>(lists,3);
    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	
    	mav.addObject("customerDto", customerDto);
    	mav.addObject("lists", lists);
    	mav.addObject("page", page);
    	mav.setViewName("mypage-qna");
    	
    	return mav;
    }
    
    //마이페이지 문의글 
    @PreAuthorize("isAuthenticated")
    @GetMapping("qna/question")
    public ModelAndView qnaQuestion(Principal principal, HttpServletRequest req) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	int serviceQueNum = Integer.parseInt(req.getParameter("serviceQueNum"));
    	ServiceQuestionDto dto = serviceQuestionService.findServiceQue(serviceQueNum);
    	
    	String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr="1";
    	}
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	
    	mav.addObject("pageNum", pageNum);
    	mav.addObject("dto", dto);
    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-qna-question");
    	
    	return mav;
    	
    }
    
    //마이페이지 답변보이게 하는 ajax
    @RequestMapping(value = "qna/getAnswer", method = RequestMethod.GET )
    @ResponseBody
    public String getAnswer(@RequestParam("queNum")int queNum) throws Exception {

    	ServiceAnswerDto dto = serviceAnswerService.getReadServiceAnsData(queNum);
    	String content = dto.getServiceAnsContent();
    	
		return content;
	}
    
    //마이페이지 답변글
    @PreAuthorize("isAuthenticated")
    @GetMapping("qna/answer")
    public ModelAndView qnaAnswer(Principal principal, HttpServletRequest req) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	int serviceAnsNum = Integer.parseInt(req.getParameter("serviceAnsNum"));
    	ServiceAnswerDto dto = serviceAnswerService.findServiceAns(serviceAnsNum);
    	
    	String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr="1";
    	}
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	
    	mav.addObject("pageNum", pageNum);
    	mav.addObject("dto", dto);
    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-qna-answer");
    	
    	return mav;
    	
    }
    
    //마이페이지 질문보이게 하는 ajax
    @RequestMapping(value = "qna/getQuestion", method = RequestMethod.GET )
    @ResponseBody
    public String getQuestion(@RequestParam("queNum")int queNum) throws Exception {

    	ServiceQuestionDto dto = serviceQuestionService.findServiceQue(queNum);
    	String content = dto.getServiceQueContent();
    	
		return content;
	}
    
//--------------------------------------------------------------------------------    
    //마이페이지 마이리뷰
    @PreAuthorize("isAuthenticated")
    @GetMapping("review")
    public ModelAndView review(Principal principal, HttpServletRequest req) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr = "1";
    	}
    	
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	Page<ReplyDto> lists = customerService.getReviewPaging(principal.getName(), pageNum);
    	
    	PageInfo<ReplyDto> page = new PageInfo<>(lists,3);
    	
    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	
    	mav.addObject("customerDto", customerDto);
    	mav.addObject("lists", lists);
    	mav.addObject("page", page);
    	
    	mav.setViewName("mypage-review");
    	
    	return mav;
    }
    
    //마이페이지 마이리뷰 수정
    @PreAuthorize("isAuthenticated")
    @PostMapping("reviewUp")
    public ModelAndView review(ReplyDto rdto, HttpServletRequest req) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	String pageNumStr = req.getParameter("pageNum");
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	replyService.updateReply(rdto);
    	
    	mav.addObject("pageNum", pageNum);
    	mav.setViewName("redirect:review");
    	
    	return mav;
    }    
    
    //마이페이지 마이리뷰 삭제
    @PreAuthorize("isAuthenticated")
    @PostMapping("reviewDel")
    public ModelAndView reviewDel(HttpServletRequest req) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();
    	String pageNumStr = req.getParameter("pageNum");
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	//point 테이블에 데이터 넣기
    	pointService.insertDelData(createPoint.reviewDelpoint(req.getParameter("customerEmail")));
    	replyService.deleteReply(Integer.parseInt(req.getParameter("replyId")));
    	
    	mav.addObject("pageNum", pageNum);
    	mav.setViewName("redirect:review");
    	
    	return mav;
    }
    
    //마이페이지 배송지관리
    @PreAuthorize("isAuthenticated")
    @GetMapping("address")
    public ModelAndView address(Principal principal) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	List<DeliverDto> lists = deliveryService.listDeliver(principal.getName());
    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	
    	mav.addObject("customerDto", customerDto);
    	mav.addObject("lists", lists);
    	
    	mav.setViewName("mypage-address");
    	
    	return mav;
    }
    
    //마이페이지 배송지 추가
    @PreAuthorize("isAuthenticated")
    @PostMapping("addressIn")
    public ModelAndView adressIn(HttpServletResponse response, DeliverDto ddto, Principal principal) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();

    	ddto.setCustomerEmail(principal.getName());
    	
    	int dup = deliveryDupChk.dupChk(ddto);
    	int maxNum = deliveryService.maxNumDeliver();
    	
    	if(dup==0) {
    		AlertRedirect.warningMessage(response, "address", "배송지가 등록되었습니다.");
    		ddto.setDeliverType("추가");
    		ddto.setDeliverNum(maxNum+1);
    		deliveryService.insertDeliver(ddto);
    	}else {
    		AlertRedirect.warningMessage(response, "중복된 배송지입니다.");
    	}
    	mav.setViewName(null);
    	return mav;
    }
    
    //마이페이지 배송지 수정
    @PreAuthorize("isAuthenticated")
    @PostMapping("addressUp")
    public ModelAndView addressUp(HttpServletResponse response, DeliverDto ddto, Principal principal) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	ddto.setCustomerEmail(principal.getName());
    	
    	int dup = deliveryDupChk.dupChk(ddto);
    	
    	if(dup==0) {
    		AlertRedirect.warningMessage(response, "address" ,"배송지가 수정되었습니다.");
    		deliveryService.updateDeliver(ddto);
    	}else {
    		AlertRedirect.warningMessage(response, "동일한 배송지가 존재합니다.");
    	}
    	mav.setViewName(null);
    	return mav;
    }
    
    //마이페이지 배송지 삭제
    @PreAuthorize("isAuthenticated")
    @PostMapping("addressDel")
    public ModelAndView addressDel(int deliverNum) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	deliveryService.deleteDeliver(deliverNum);
    	
    	mav.setViewName("redirect:address");
    	
    	return mav;
    }
    
    //마이페이지 포인트
    @PreAuthorize("isAuthenticated")
    @GetMapping("point")
    public ModelAndView point(Principal principal) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	List<PointDto> lists = pointService.getList(principal.getName());
    	int totalPoint = pointService.getTotal(principal.getName());
    	
    	mav.addObject("customerDto", customerDto);
    	mav.addObject("lists", lists);
    	mav.addObject("totalPoint", totalPoint);
    	
    	mav.setViewName("mypage-point");
    	
    	return mav;
    }
    
    //마이페이지 회원탈퇴 화면
    @PreAuthorize("isAuthenticated")
    @GetMapping("withdraw")
    public ModelAndView withdraw(Principal principal) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	
    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-withdraw");
    	
    	return mav;
    }
    
    //마이페이지 회원탈퇴 처리
    @PreAuthorize("isAuthenticated")
    @PostMapping("withdraw")
    public ModelAndView withdraw_ok(Principal principal) {
    	ModelAndView mav = new ModelAndView();
    	
    	customerService.deleteData(principal.getName());
    	
    	mav.setViewName("redirect:/");
    	
    	return mav;
    }

}
