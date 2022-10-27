package com.exe.cozy.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ServiceAnswerDto;
import com.exe.cozy.mapper.ServiceAnswerMapper;
import com.exe.cozy.service.ServiceAnswerService;

@Service("serviceAnswerService")
public class ServiceAnswerServiceImpl implements ServiceAnswerService {

	@Autowired private ServiceAnswerMapper serviceAnswerMapper;
	
	@Override
	public ServiceAnswerDto findServiceAns(int serviceAnsNum) throws Exception{
		return serviceAnswerMapper.findServiceAns(serviceAnsNum);
	}

}
