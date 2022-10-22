package com.exe.cozy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemQnaDto {

	private int itemQnaNum;
	private int itemNum;
	private String itemQnaTitle;
	private String customerEmail;
	private Date ItemQnaCreate;
	private String itemQnaContent;

	
	
	
}
