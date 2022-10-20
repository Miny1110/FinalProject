package com.exe.cozy.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDto {
	
	private String customerEmail;
	private String customerPwd;
	private String customerName;
	private String customerTel;
	private String customerZipCode;
	private String customerRAddr;
	private String customerJAddr;
	private String customerDAddr;
	private String customerProfile;
	private Date customerDate;
	private String customerGrade;
	private int customerType;
	private int customerPoint;
	private String userRole;
	

}
