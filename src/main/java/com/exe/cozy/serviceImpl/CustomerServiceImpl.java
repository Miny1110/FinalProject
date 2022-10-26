package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.OrderDetailDto;
import com.exe.cozy.domain.OrderDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.domain.ServiceQuestionDto;
import com.exe.cozy.mapper.CustomerMapper;
import com.exe.cozy.service.CustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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
		dto.setCustomerPwd(passwordEncoder.encode(dto.getCustomerPwd()));
		customerMapper.updatePwd(dto);
	}

	@Override
	public CustomerDto getReadData(String customerEmail) {
		return customerMapper.getReadData(customerEmail);		
	}

	@Override
	public void updateData(CustomerDto dto) {
		dto.setCustomerPwd(passwordEncoder.encode(dto.getCustomerPwd()));
		customerMapper.updateData(dto);
	}

	@Override
	public void deleteData(String customerEmail) {
		SecurityContextHolder.clearContext();
		customerMapper.deleteData(customerEmail);
	}

	@Override
	public void updatePoint(CustomerDto dto) {
		customerMapper.updatePoint(dto);
	}

	@Override
	public Page<ReplyDto> getReviewPaging(String customerEmail, int pageNum){
		PageHelper.startPage(pageNum, 5);
		return customerMapper.getReviewPaging(customerEmail, pageNum);
	}

	@Override
	public Page<ServiceQuestionDto> getQnaList(String customerEmail, String searchKey, String searchValue,
			int pageNum) {
		PageHelper.startPage(pageNum, 2);
		return customerMapper.getQnaList(customerEmail, searchKey, searchValue, pageNum);
	}

	@Override
	public Page<OrderDto> getOrderList(String customerEmail, int pageNum) {
		PageHelper.startPage(pageNum, 2);
		return customerMapper.getOrderList(customerEmail, pageNum);
	}

	@Override
	public List<OrderDto> getOrderDetailList(String customerEmail) {
		return customerMapper.getOrderDetailList(customerEmail);
	}

	@Override
	public OrderDto getOrderDetail(String customerEmail, String orderNum) {
		return customerMapper.getOrderDetail(customerEmail, orderNum);
	}

	@Override
	public List<OrderDetailDto> getOrderDetailOne(String customerEmail, String orderNum) {
		return customerMapper.getOrderDetailOne(customerEmail, orderNum);
	}


}
