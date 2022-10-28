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
	public int serviceAnsMaxNum() throws Exception {
		
		return serviceAnswerMapper.serviceAnsMaxNum();
	}

	@Override
	public ServiceAnswerDto findServiceAns(int serviceAnsNum) throws Exception{
		return serviceAnswerMapper.findServiceAns(serviceAnsNum);
	}

	@Override
	public ServiceAnswerDto getReadServiceAnsData(int serviceAnsNum) throws Exception {
		return serviceAnswerMapper.getReadServiceAnsData(serviceAnsNum);
	}

	@Override
	public void insertServiceAns(ServiceAnswerDto sadto) throws Exception {
		
		serviceAnswerMapper.insertServiceAns(sadto);
	}

	@Override
	public void updateServiceAns(ServiceAnswerDto sadto) throws Exception {
		
		serviceAnswerMapper.updateServiceAns(sadto);
	}

	@Override
	public void deleteServiceAns(int serviceAnsNum) throws Exception {
		
		serviceAnswerMapper.deleteServiceAns(serviceAnsNum);
	}

	
}
