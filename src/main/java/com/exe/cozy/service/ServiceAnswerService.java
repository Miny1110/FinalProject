package com.exe.cozy.service;

import com.exe.cozy.domain.ServiceAnswerDto;

public interface ServiceAnswerService {

	public ServiceAnswerDto findServiceAns(int serviceAnsNum) throws Exception;
	public ServiceAnswerDto getReadServiceAnsData(int serviceAnsNum) throws Exception;
	
}
