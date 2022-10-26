package com.exe.cozy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exe.cozy.service.CategoryService;

@Controller
public class CategoryController {

	@Resource
	private CategoryService CategoryService;
	
	@RequestMapping("/category")
	public String category() {
		return "shopCategory";
	}
	
	
	
	
}
