package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.ReplyDto;
import com.github.pagehelper.Page;

public interface CustomerService {

	public int emailChk(String customerEmail);
	
	public void insertData(CustomerDto dto);
	
	public CustomerDto getLogin(String customerEmail);
	public CustomerDto forgot(String customerEmail);
	public CustomerDto getReadData(String customerEmail);
	public void updatePwd(CustomerDto dto);
	
	public void updateData(CustomerDto dto);
	public void updatePoint(CustomerDto dto);
	public void deleteData(String customerEmail);
	
//	public List<ReplyDto> getReviewList(String customerEmail);
	public Page<ReplyDto> getReviewPaging(String customerEmail, int pageNum);
}
