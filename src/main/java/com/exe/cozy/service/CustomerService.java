package com.exe.cozy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.OrderDetailDto;
import com.exe.cozy.domain.OrderDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.domain.ServiceAnswerDto;
import com.exe.cozy.domain.ServiceQuestionDto;
import com.github.pagehelper.Page;

public interface CustomerService {

	public int emailChk(String customerEmail);
	
	public void insertData(CustomerDto dto);
	
	public CustomerDto getLogin(String customerEmail);
	public CustomerDto forgot(String customerEmail);
	public CustomerDto getReadData(String customerEmail);
	public void updatePwd(CustomerDto dto);
	
	public void updateData(CustomerDto dto);
	public void updateProfile(CustomerDto dto);
	public void updatePoint(CustomerDto dto);
	public void deleteData(String customerEmail);
	
//	public List<ReplyDto> getReviewList(String customerEmail);
	public Page<ReplyDto> getReviewPaging(String customerEmail, int pageNum);
	
	public Page<ServiceQuestionDto> getQnaList(String customerEmail,String searchKey,String searchValue,int pageNum);

	public Page<OrderDto> getOrderList(String customerEmail,int pageNum);
	public List<OrderDto> getOrderDetailList(String customerEmail);
	
	public OrderDto getOrderDetail(String customerEmail, String orderNum);
	public List<OrderDetailDto> getOrderDetailOne(String customerEmail, String orderNum);
	
	public Page<OrderDto> getOrderCancleList(String customerEmail, int pageNum);
	public List<OrderDetailDto> getOrderCancleDetailList(String customerEmail);
	
	public ServiceAnswerDto getReadAnsData(int serviceQueNum);
	
	
	public void stateUp(String orderNum);
}
