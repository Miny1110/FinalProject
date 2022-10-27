package com.exe.cozy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemQuestionDto {//ItemQuestion

	private int itemQueNum;
	private int itemNum;
	private String itemQueTitle;
	private String customerEmail;
	private Date itemQueCreate;
	private String itemQueContent;

	private ItemAnswerDto answer;
	
	
}
