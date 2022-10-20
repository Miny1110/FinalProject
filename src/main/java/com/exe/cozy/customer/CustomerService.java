package com.exe.cozy.customer;

import com.exe.cozy.domain.CustomerDto;

public interface CustomerService {

	public int emailChk(String customerEmail);
	
	public void insertData(CustomerDto dto);
	
	public CustomerDto getLogin(String customerEmail);
	public CustomerDto forgot(String customerEmail);
	public CustomerDto getReadData(String customerEmail);
	public void updatePwd(CustomerDto dto);
	
	public void updateData(CustomerDto dto);
	public void deleteData(String customerEmail);
	
}
