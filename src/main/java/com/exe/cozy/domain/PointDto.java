package com.exe.cozy.domain;

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
	private String pointDate;
	private String pointStartDate;
	private String pointEndDate;
	private String customerEmail;
	
}
