package com.exe.cozy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointDto {

	private int pointNum;
	private String pointTitle;
	private String pointContent;
	private int pointAmount;
	private String pointState;
	private Date pointDate;
	private Date pointStartDate;
	private Date pointEndDate;
	private String customerEmail;
	
}
