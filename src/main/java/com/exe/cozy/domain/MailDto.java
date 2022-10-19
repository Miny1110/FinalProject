package com.exe.cozy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MailDto {
	
	private String toAddress;
	private String title;
	private String message;
	private String fromAddress;

}
