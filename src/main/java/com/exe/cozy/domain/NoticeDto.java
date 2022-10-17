package com.exe.cozy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDto {

	private int serviceNoticeNum; 
	private String serviceNoticeTitle;
	private String serviceNoticeContent;
	private String serviceNoticeDate;
	
}
