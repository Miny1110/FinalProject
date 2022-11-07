package com.exe.cozy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDto {

	private int noticeNum;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
}
