package com.exe.cozy.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeliverDto {
	
	private int deliverNum;
	private String customerEmail;
	private String deliverName;
	private String deliverRAddr;
	private String deliverJAddr;
	private String deliverDAddr;
	private String deliverZipCode;
	private String deliverTel;
	
}