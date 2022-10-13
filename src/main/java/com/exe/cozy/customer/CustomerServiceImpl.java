package com.exe.cozy.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.mapper.CustomerMapper;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public String test() {
		return customerMapper.test();
	}
	
	@Override
	public int emailChk(String customerEmail) {
		return customerMapper.emailChk(customerEmail);
	}

	@Override
	public void insertData(CustomerDto dto) {
		customerMapper.insertData(dto);
	}

	@Override
	public CustomerDto getReadData(String customerEmail) {
		return customerMapper.getReadData(customerEmail);
	}

	@Override
	public void updateData(CustomerDto dto) {
		customerMapper.updateData(dto);
	}

	@Override
	public void deleteData(String customerEmail) {
		customerMapper.deleteData(customerEmail);
	}

}
