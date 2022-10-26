package com.exe.cozy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.service.CategoryService;


@Controller
public class CategoryController {

	@Resource
	private CategoryService CategoryService;
	
	@GetMapping("category")
	public ModelAndView category() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("shopCategory");
		
		return mav;
	}
	
	
	
	
}
