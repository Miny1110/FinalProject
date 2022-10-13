package com.exe.cozy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	
	private String customerEmail;
	private String customerPwd;
	private String customerName;
	private String customerTel;
	private int customerZipCode;
	private String customerRAddr;
	private String customerJAddr;
	private String customerDAddr;
	private String customerProfile;
	private Date customerDate;
	private String customerGrade;
	private int customerType;
	private int customerPoint;
	


}
