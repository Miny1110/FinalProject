package com.exe.cozy.customer;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.mapper.CustomerMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerMapper customerMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public int emailChk(String customerEmail) {
		return customerMapper.emailChk(customerEmail);
	}
	
	@Override
	public void insertData(CustomerDto dto) {
		//비밀번호는 암호화해서 저장
		//BCrypt 해싱 함수를 사용해서 암호화
		dto.setCustomerPwd(passwordEncoder.encode(dto.getCustomerPwd()));
		
		customerMapper.insertData(dto);
	}

	@Override
	public CustomerDto getLogin(String customerEmail) {
		return customerMapper.getLogin(customerEmail);
	}

	@Override
	public CustomerDto forgot(String customerEmail) {
		return customerMapper.forgot(customerEmail);
	}
	
	@Override
	public void updatePwd(CustomerDto dto) {
		customerMapper.updatePwd(dto);
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

	@Override
	public void updatePoint(CustomerDto dto) {
		customerMapper.updatePoint(dto);
	}

	@Override
	public List<ReplyDto> getReviewList(String customerEmail) {
		return customerMapper.getReviewList(customerEmail);
	}

}
