package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.ServiceQuestionDto;
import com.github.pagehelper.Page;


@Mapper
public interface ServiceQuestionMapper {

	public int serviceQueMaxNum() throws Exception;

	public void insertServiceQue(ServiceQuestionDto sqdto) throws Exception;
	
	public  ServiceQuestionDto findServiceQue1 (int serviceQueNum);

	public ServiceQuestionDto findServiceQue(int serviceQueNum);
	
	public ServiceQuestionDto getReadServiceQueData(int serviceQueNum) throws Exception;

	public List<ServiceQuestionDto> getReadServiceQueList(int serviceQueNum) throws Exception;
	
	//질문 페이징처리 목록
	public Page<ServiceQuestionDto> getServiceQuePaging(@Param("pageNum") int pageNum);
	
	public void updateServiceQue(ServiceQuestionDto sqdto) throws Exception;
	
	public void deleteServiceQue(int serviceQueNum);
	
	
}
