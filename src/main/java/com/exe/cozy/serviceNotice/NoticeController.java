package com.exe.cozy.serviceNotice;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	
	
	@GetMapping("/notice")
	public void noticeServiceGET(Model model) {
		
		model.addAttribute("list",noticeService.getNoticeList());
		
	}
	
}
