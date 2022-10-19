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
	
	
	@PostMapping("/noticeCreate_ok")
	public ModelAndView noticeCreate_ok(NoticeDto ndto,HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		int MaxNoticeNum = noticeService.maxNoticeNum();
		
		ndto.setNoticeNum(MaxNoticeNum + 1);
		
		noticeService.insertNoticeData(ndto);
		
		mav.setViewName("redirect:/noticeList");
		return mav;
	}
	
	//공지 리스트 띄우기
	@GetMapping("/noticeList")
	public ModelAndView noticeList(HttpServletRequest request) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchKey == null) {
			searchKey = "noticeTitle";
			searchValue = "";
		} else {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}
		
		int noticeDataCount = 0;
		//전체 데이터 갯수
		try {
			
			noticeDataCount = noticeService.getNoticeDataCount(searchKey,searchValue);
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
		
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
		
		
		List<NoticeDto> nlists = noticeService.getNoticeLists(start, end, searchKey,searchValue);
		
		
		String param = "";
		if(searchValue!=null && !searchValue.equals("")) {
			param = "searchKey=" + searchKey;
			param = "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		String listUrl = "/noticeList";
		
		
		if(!param.equals("")) {
			listUrl += "?" + param;
		}
		
		
		String pageIndexList = myPage.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/noticeArticle?pageNum=" + currentPage;
		
		if(!param.equals("")) {
			articleUrl += "&" + param;
		}
		
		ModelAndView mav = new ModelAndView();
		
		//포워딩할 데이터
		mav.addObject("nlists",nlists);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("NoticeDataCount",noticeDataCount);
		mav.addObject("pageNum", currentPage);
		
		mav.setViewName("noticeList");
		return mav;
	}
	
	
	@GetMapping("/noticeArticle")
	public ModelAndView articleList(HttpServletRequest request) throws Exception {
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null && !searchValue.equals("")) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		NoticeDto ndto = noticeService.getReadNoticeData(noticeNum);
		
		if(ndto == null) {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/noticeList?pageNum=" + pageNum);
			
			return mav;
		}
		
		int lineSu = ndto.getNoticeContent().split("\n").length;
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null && !searchValue.equals("")) {
			
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ndto", ndto);
		mav.addObject("params", param);
		mav.addObject("lineSu", lineSu);
		mav.addObject("pageNum", pageNum);
		
		mav.setViewName("noticeArticle");
		
		return mav;
		
		
	
	}
	
	
	
	
}
