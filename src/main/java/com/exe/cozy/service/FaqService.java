package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.FaqDto;

public interface FaqService {

	/* faq 번호*/
	public int maxFaqNum() throws Exception;
	
	/* faq 등록*/
	public void insertFaqData(FaqDto fdto) throws Exception;
	
	/* faq 데이터 읽기*/
	public FaqDto getReadFaqData(int faqNum) throws Exception;
	
	public List<FaqDto> getReadFaqList() throws Exception;
	
	public List<FaqDto> getFaqType1() throws Exception;
	public List<FaqDto> getFaqType2() throws Exception;
	public List<FaqDto> getFaqType3() throws Exception;
	
	/* faq 수정*/
	public void updateFaqData(FaqDto fdto) throws Exception;
	
	/* faq 삭제*/
	public void deleteFaqData(int faqNum) throws Exception;
	
	
}
