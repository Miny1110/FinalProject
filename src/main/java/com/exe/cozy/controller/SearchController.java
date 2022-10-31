package com.exe.cozy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.ServiceQuestionDto;
import com.exe.cozy.service.SearchService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Controller
public class SearchController {                                               

	@Resource
	private SearchService searchService;
	
	@GetMapping("search")
	public ModelAndView search(HttpServletRequest req) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
    	String searchValue = req.getParameter("searchValue");
    	if(searchValue==null) {
    		searchValue="";
    	}
    	
//    	String pageNumStr = req.getParameter("pageNum");
//    	if(pageNumStr==null) {
//    		pageNumStr="1";
//    	}
//    	int pageNum = Integer.parseInt(pageNumStr);
//    	
//    	Page<ItemDetailDto> lists = searchService.searchData(searchValue, pageNum);
//    	
//    	PageInfo<ItemDetailDto> page = new PageInfo<>(lists,3);
    	
//    	List<ItemDetailDto> lists = searchService.searchData(searchValue, pageNum);
    	List<ItemDetailDto> lists = searchService.searchData(searchValue);
    	
    	
    	mav.setViewName("wishlist");
		mav.addObject("lists",lists);
//		mav.addObject("page", page);
		
		return mav;
	}
	
	
}
