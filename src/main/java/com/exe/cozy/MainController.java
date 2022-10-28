package com.exe.cozy;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	public ModelAndView home(Principal principal, HttpServletRequest req) throws Exception {

		ModelAndView mav = new ModelAndView();

		List<ItemDetailDto> tlists = mainHomeService.selectTodaydeal();


		mav.addObject("tlists", tlists);
		mav.setViewName("index");


		return mav;
	}




}
