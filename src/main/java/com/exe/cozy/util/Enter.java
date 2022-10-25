package com.exe.cozy.util;

import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ReplyDto;
import com.github.pagehelper.Page;

@Service
public class Enter {

	public ReplyDto insertEnter(ReplyDto rdto) {
		
		String content = rdto.getContent();
		content.replaceAll("\r\n", "<br/>");
		rdto.setContent(content);
		
		return rdto;
	}
	
	public Page<ReplyDto> printEnter(Page<ReplyDto> lists) {
		
		for(ReplyDto rdto : lists) {
			String content = rdto.getContent();
			content.replaceAll("<br/>", "\r\n");
			rdto.setContent(content);
		}
		
		return lists;
	}
	
}
