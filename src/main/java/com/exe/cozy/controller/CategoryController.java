package com.exe.cozy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.service.CategoryService;
import com.exe.cozy.service.ItemDetailService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;


@Controller
public class CategoryController {

	@Resource
	private CategoryService CategoryService;
	
	@GetMapping("category")
	public ModelAndView category(HttpServletRequest req) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		String itemMainType = req.getParameter("itemMainType");
		
		String itemSubType = req.getParameter("itemSubType");
		
		if(itemMainType==null) {
			itemMainType="1";
		}

		if(itemSubType==null) {
			itemSubType="";
		}
		
		String pageNumStr = req.getParameter("pageNum");
    	if(pageNumStr==null) {
    		pageNumStr = "1";
    	}
    	
    	int pageNum = Integer.parseInt(pageNumStr);
    	
    	int totalCount = CategoryService.totalCount(itemMainType, itemSubType);
    	
    	Page<ItemDetailDto> lists = CategoryService.selectCategory(itemMainType,itemSubType, pageNum);

    	PageInfo<ItemDetailDto> page = new PageInfo<>(lists,3);
    	
    	
    	
    	mav.addObject("lists", lists);
    	mav.addObject("page", page);
    	mav.addObject("itemMainType",itemMainType);
    	mav.addObject("itemSubType",itemSubType);
    	mav.addObject("totalCount",totalCount);
		
		mav.setViewName("shopCategory");
		
		return mav;
	}
	
	
	
	
	
	
}
