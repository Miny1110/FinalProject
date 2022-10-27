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


@Controller
public class CategoryController {

	@Resource
	private CategoryService CategoryService;
	
	@GetMapping("category")
	public ModelAndView category(HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView();
		
		String itemMainType = req.getParameter("itemMainType");
		
		String itemSubType = req.getParameter("itemSubType");
		
		if(itemMainType==null) {
			itemMainType="1";
		}

		if(itemSubType==null) {
			itemSubType="";
		}
		
		System.out.println(itemMainType);
		System.out.println(itemSubType);
		
		List<ItemDetailDto> lists = CategoryService.selectCategory(itemMainType,itemSubType);
		
		mav.addObject("lists",lists);	
		mav.setViewName("shopCategory");
		
		return mav;
	}
	
	
	
	
	
	
}
