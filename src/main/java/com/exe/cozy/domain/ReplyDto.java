package com.exe.cozy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDto {

	private int replyId;
	private int itemNum;
	private String customerEmail;
	private Date regDate;
	private String content;
	private String rating;
	
	private ItemDetailDto item;
}
