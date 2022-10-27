package com.exe.cozy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAnswerDto {//itemAnswer

	private int itemAnsNum;
	private int itemQueNum;
	private Date itemAnsCreate;
	private String itemAnsContent;


	
}
