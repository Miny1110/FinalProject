package com.exe.cozy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.service.CategoryService;


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
		
		mav.setViewName("shopCategory");
		
		return mav;
	}
	
	
	
	
}
