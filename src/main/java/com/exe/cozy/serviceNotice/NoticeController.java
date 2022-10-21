package com.exe.cozy.serviceNotice;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.NoticeDto;
import com.exe.cozy.util.MyPage;


@Controller
public class NoticeController {

	@Resource
	private NoticeService noticeService;

	@Autowired
	MyPage myPage;
	
	
	//공지 입력창 띄우기
	@GetMapping("/noticeCreate") 
	public ModelAndView noticeCreate() throws Exception {
	 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("noticeCreate");
		return mav;
	
	}
	
	
	@PostMapping("/noticeCreate")
	public ModelAndView noticeCreate_ok(NoticeDto ndto,HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		int MaxNoticeNum = noticeService.maxNoticeNum();
		
		ndto.setNoticeNum(MaxNoticeNum + 1);
		
		noticeService.insertNoticeData(ndto);
		
		mav.setViewName("redirect:/noticeList");
		return mav;
	}
	
	//공지 리스트 띄우기
	@RequestMapping(value = "/noticeList", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView noticeList(NoticeDto ndto, HttpServletRequest request) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		String searchNoticeKey =request.getParameter("searchNoticeKey");
		String searchNoticeValue = request.getParameter("searchNoticeValue");
		
		if(searchNoticeValue == null) {
			searchNoticeKey = "searchNoticeKey";
			searchNoticeValue = "";
		} else {
			if(request.getMethod().equals("GET")) {
				searchNoticeValue = URLDecoder.decode(searchNoticeValue, "UTF-8");
			}
		}
		
		//전체 데이터 갯수
		int noticeDataCount = noticeService.getNoticeDataCount(searchNoticeKey,searchNoticeValue);
		
		//페이지에 표시되는 제목 갯수
		int numPerPage = 10;
		
		//전체 페이지 갯수
		int totalPage = myPage.getPageCount(numPerPage, noticeDataCount);
		
		//삭제했을 때 제일 마지막 페이지에 보이는 데이터 처리
		if(currentPage > totalPage) {
			currentPage = totalPage;
		}
		
		//rowNum에 시작과 끝 값
		int start = (currentPage-1) * numPerPage + 1;
		int end = currentPage * numPerPage;
		
		List<NoticeDto> nlists = null;
		
		try {
			nlists = noticeService.getNoticeLists(start, end, searchNoticeKey, searchNoticeValue);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		String param = "";
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			param = "searNoticechKey=" + searchNoticeKey;
			param+= "&searchNoticeValue=" + URLEncoder.encode(searchNoticeValue, "UTF-8");
		}
		
		String listUrl = "/noticeList";
		
		if(!param.equals("")) {
			listUrl += "?" + param;
		}
		
		String pageIndexList = myPage.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/article.action?pageNum=" + currentPage;
		
		if(!param.equals("")) {
			articleUrl += "&" + param;
		}
		ModelAndView mav = new ModelAndView();
		
		//포워딩할 데이터
		mav.addObject("nlists",nlists);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("noticeDataCount", noticeDataCount);
		mav.addObject("pageNum", currentPage);
		
		mav.setViewName("noticeList");
		return mav;
	}
	
	
	@GetMapping("/noticeArticle")
	public ModelAndView articleList(HttpServletRequest request) throws Exception {
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		String pageNum = request.getParameter("pageNum");
		
		String searchNoticeKey = request.getParameter("searchNoticeKey");
		String searchNoticeValue = request.getParameter("searchNoticeValue");
		
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			searchNoticeValue = URLDecoder.decode(searchNoticeValue, "UTF-8");
		}
		
		NoticeDto ndto = noticeService.getReadNoticeData(noticeNum);
		
		if(ndto == null) {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/noticeList?pageNum=" + pageNum);
			
			return mav;
		}
		
		int lineSu = ndto.getNoticeContent().split("\n").length;
		
		String param = "pageNum=" + pageNum;
		
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			
			param += "&searchNoticeKey=" + searchNoticeKey;
			param += "&searchNoticeValue=" + URLEncoder.encode(searchNoticeValue, "UTF-8");
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ndto", ndto);
		mav.addObject("params", param);
		mav.addObject("lineSu", lineSu);
		mav.addObject("pageNum", pageNum);
		
		mav.setViewName("noticeArticle");
		
		return mav;
	}
	
	
	//update
	@GetMapping("/noticeUpdate")
	public ModelAndView noticeUpdate(HttpServletRequest request) throws Exception{
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		String pageNum = request.getParameter("pageNum");
		
		String searchNoticeKey = request.getParameter("searchNoticeKey");
		String searchNoticeValue = request.getParameter("searchNoticeValue");
		
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			searchNoticeValue = URLDecoder.decode(searchNoticeValue, "UTF-8");
		}
		
		NoticeDto ndto = noticeService.getReadNoticeData(noticeNum);
		
		if(ndto == null) {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/noticeList?pageNum=" + pageNum);
			
			return mav;
		}
		
		String param = "pageNum=" + pageNum;
		
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			
			param += "&searchNoticeKey=" + searchNoticeKey;
			param += "&searchNoticeValue=" + URLEncoder.encode(searchNoticeValue, "UTF-8");
			
		}
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("ndto", ndto);
		mav.addObject("pageNum", pageNum);
		mav.addObject("params", param);
		mav.addObject("searchNoticeKey", searchNoticeKey);
		mav.addObject("searchNoticeValue", searchNoticeValue);
		
		mav.setViewName("noticeUpdate");
		
		return mav;
	}
	
	
	
	@PostMapping("/noticeUpdate")
	public ModelAndView noticeUpdate_ok(NoticeDto ndto, HttpServletRequest request) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		String searchNoticeKey = request.getParameter("searchNoticeKey");
		String searchNoticeValue = request.getParameter("searchNoticeValue");
		
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			searchNoticeValue = URLDecoder.decode(searchNoticeValue, "UTF-8");
		}
		
		noticeService.updateNoticeData(ndto);
		
		String param = "pageNum=" + pageNum;
		
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			
			param += "&searchNoticeKey=" + searchNoticeKey;
			param += "&searchNoticeValue=" + URLEncoder.encode(searchNoticeValue, "UTF-8");
			
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/noticeList?" + param);
		
		
		return mav;
	}
	
	
	@GetMapping("/delete")
	public ModelAndView delete_ok(HttpServletRequest request) throws Exception{
		
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		String pageNum = request.getParameter("pageNum");
		
		String searchNoticeKey = request.getParameter("searchNoticeKey");
		String searchNoticeValue = request.getParameter("searchNoticeValue");
		
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			searchNoticeValue = URLDecoder.decode(searchNoticeValue, "UTF-8");
		}
		
		noticeService.deleteNoticeData(noticeNum);
		
		String param = "pageNum=" + pageNum;
		
		if(searchNoticeValue!=null && !searchNoticeValue.equals("")) {
			
			param += "&searchNoticeKey=" + searchNoticeKey;
			param += "&searchNoticeValue=" + URLEncoder.encode(searchNoticeValue, "UTF-8");
			
			
		}
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/noticeList?" + param);
		
		return mav;
		
	}
	
	
}
