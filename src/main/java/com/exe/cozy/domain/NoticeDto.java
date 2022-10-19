package com.exe.cozy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDto {

	private int noticeNum; 
	private String noticeTitle;
	private String noticeContent;
	private String noticeDate;
	
}
