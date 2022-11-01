package com.exe.cozy;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.exe.cozy.domain.CartDto;
import com.exe.cozy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.service.CustomerService;
import com.exe.cozy.service.MainHomeService;

@Controller
public class MainController {

	@Resource
	private MainHomeService mainHomeService;

	@Autowired CustomerService customerService;


	@RequestMapping("/")
	public ModelAndView home(Principal principal, HttpServletRequest req, HttpSession session) throws Exception {

		ModelAndView mav = new ModelAndView();

		//오늘의 딜
		List<ItemDetailDto> tlists = mainHomeService.selectTodaydeal();
		
		//best 상품
		List<ItemDetailDto> hlists = mainHomeService.selectHitcount();

		//신상품
		List<ItemDetailDto> nlists = mainHomeService.selectNewItem();

		mav.addObject("tlists", tlists);
		mav.addObject("hlists", hlists);
		mav.addObject("nlists",nlists);

		mav.setViewName("index");


		return mav;
	}




}
