package com.exe.cozy.mail;

import com.exe.cozy.domain.MailDto;

public interface MailService {
	
	public MailDto createMail(String tmpPwd, String customerEmail);
	public void sendMail(MailDto dto);

}
