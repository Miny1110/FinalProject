package com.exe.cozy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.ServiceAnswerDto;

@Mapper
public interface ServiceAnswerMapper {

	public ServiceAnswerDto findServiceAns(int serviceAnsNum) throws Exception;
	public ServiceAnswerDto getReadServiceAnsData(int serviceAnsNum) throws Exception;
}
