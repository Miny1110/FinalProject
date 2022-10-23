package com.exe.cozy.customer;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.exe.cozy.delivery.DeliveryDupChk;
import com.exe.cozy.delivery.DeliveryService;
import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.domain.MailDto;
import com.exe.cozy.domain.PointDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.itemDetail.ReplyService;
import com.exe.cozy.mail.MailService;
import com.exe.cozy.point.PointService;
import com.exe.cozy.util.AddDate;
import com.exe.cozy.util.AlertRedirect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource private CustomerService customerService;
	@Resource private PointService pointService;
	@Resource private MailService mailService;
	@Resource private DeliveryService deliveryService;
	@Resource private ReplyService replyService;
	
	@Autowired AddDate addDate;
	@Autowired CustomerChk customerChk;
	@Autowired DeliveryDupChk deliveryDupChk;
    
	//이메일 중복확인
    @RequestMapping(value = "/emailChk", method = RequestMethod.POST )
    @ResponseBody
    public int nameCheck(@RequestParam("email") String email) {
    	int cnt = customerService.emailChk(email);
		return cnt;
	}
    
    //회원가입 화면
    @GetMapping("signUp")
    public ModelAndView signUp() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("sign-up");
    	
    	return mav;
    }
    
    //회원가입
    @PostMapping("signUp")
    public ModelAndView signUp(@ModelAttribute CustomerDto dto, BindingResult bindingResult, HttpServletRequest req) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	PointDto pointDto = new PointDto();
    	
    	int pointNum = pointService.maxNum();
    	pointDto.setPointNum(pointNum+1);
    	pointDto.setPointTitle("회원가입");
    	pointDto.setPointContent("회원가입 축하 포인트");
    	pointDto.setPointAmount(3000);
    	pointDto.setPointState("지급");
    	pointDto.setPointEndDate(addDate.addDate(30));
    	pointDto.setCustomerEmail(dto.getCustomerEmail());
    	
    	if(bindingResult.hasErrors()) {
    		mav.setViewName("sign-up");
    		return mav;
    	}
    	
    	//customer 테이블에 데이터 넣기
    	customerService.insertData(dto);
    	
    	//point 테이블에 데이터 넣기
    	pointService.insertData(pointDto);
    	
    	//로그인 화면으로 이동
    	mav.setViewName("redirect:login");
    	return mav;
    	
    }
    
    //로그인 화면
    @GetMapping("login")
    public ModelAndView login() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("log-in");
    	
    	return mav;
    	
    }
    
    //비밀번호찾기 화면
    @GetMapping("forgot")
    public ModelAndView forgot() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("forgot");
    	
    	return mav;
    }
    
    //비밀번호찾기
    @PostMapping("forgot")
    public ModelAndView forgot(String customerEmail, String customerTel, HttpServletRequest req, RedirectAttributes rattr) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	boolean check = customerChk.forgotCheck(customerEmail, customerTel);
    	
    	if(!check) {
    		rattr.addFlashAttribute("msg", "회원정보가 없습니다.");
    		
    		mav.setViewName("redirect:forgot");
    		
    		return mav;
    	}

    	//임시비밀번호 발급
    	String customerPwd = customerChk.getTmpPwd();
    	
    	//이메일발송
    	MailDto mailDto = mailService.createMail(customerPwd, customerEmail);
    	mailService.sendMail(mailDto);
    	
    	//임시비밀번호 저장
    	CustomerDto dto = new CustomerDto();
    	dto.setCustomerEmail(customerEmail);
    	dto.setCustomerPwd(customerPwd);
    	
    	customerService.updatePwd(dto);
    	
    	mav.setViewName("redirect:sendEmail");
    	
    	return mav;
    }
    
    
    //비밀번호찾기 이메일전송 화면
    @GetMapping("sendEmail")
    public ModelAndView sendEmail() {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("sendEmail");
    	
    	return mav;
    }    
    
    
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
    public ModelAndView order(Principal principal) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	CustomerDto customerDto = customerService.getReadData(principal.getName());

    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-order");
    	
    	return mav;
    }
    
    //마이페이지 주문상세조회
    @PreAuthorize("isAuthenticated")
    @GetMapping("order/detail")
    public ModelAndView orderDetail() {
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("invoice");
    	
    	return mav;
    }       
    
    //마이페이지 주문취소조회
    @PreAuthorize("isAuthenticated")
    @GetMapping("order/cancle")
    public ModelAndView orderCancle(Principal principal) {
    	
    	ModelAndView mav = new ModelAndView();

    	CustomerDto customerDto = customerService.getReadData(principal.getName());

    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-order-cancle");
    	
    	return mav;
    }
    
    //마이페이지 주문취소상세조회
    @PreAuthorize("isAuthenticated")
    @GetMapping("order/cancle/detail")
    public ModelAndView orderCancleDetail() {
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("invoice-cancle");
    	
    	return mav;
    } 
    
    //마이페이지 문의답변
    @PreAuthorize("isAuthenticated")
    @GetMapping("qna")
    public ModelAndView qna(Principal principal) {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	CustomerDto customerDto = customerService.getReadData(principal.getName());
    	
    	mav.addObject("customerDto", customerDto);
    	mav.setViewName("mypage-qna");
    	
    	return mav;
    }
    
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
    public ModelAndView review(int replyId, HttpServletRequest req) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();
    	
    	String pageNumStr = req.getParameter("pageNum");
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	replyService.deleteReply(replyId);
    	
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
