package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.FaqDto;
import com.github.pagehelper.Page;

@Mapper
public interface FaqMapper {
    
	/* faq 번호*/
	public int maxFaqNum() throws Exception;
	
	/* faq 등록*/
	public void insertFaqData(FaqDto fdto) throws Exception;
	
	/* faq 페이징*/
	//public Page<FaqDto> getFaqLists(@Param("pageNum") int pageNum) throws Exception;
	
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

