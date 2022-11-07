package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ServiceQuestionDto;
import com.exe.cozy.mapper.ServiceQuestionMapper;
import com.exe.cozy.service.ServiceQuestionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
public class ServiceQuestionServiceImpl  implements ServiceQuestionService{
	
	@Autowired
	private ServiceQuestionMapper svcQueMapper;
	
	@Override
	public int serviceQueMaxNum() throws Exception {
		
		return svcQueMapper.serviceQueMaxNum();
	}

	@Override
	public void insertServiceQue(ServiceQuestionDto sqdto) throws Exception {
		
		svcQueMapper.insertServiceQue(sqdto);
	}

	@Override
	public ServiceQuestionDto findServiceQue1(int serviceQueNum) throws Exception {
		
		return svcQueMapper.findServiceQue1(serviceQueNum);
	}
	
	@Override
	public ServiceQuestionDto findServiceQue(int serviceQueNum) throws Exception {
		
		return svcQueMapper.findServiceQue(serviceQueNum);
	}
	
	

	@Override
	public ServiceQuestionDto getReadServiceQueData(int serviceQueNum) throws Exception {
		
		return svcQueMapper.getReadServiceQueData(serviceQueNum);
	}

	@Override
	public List<ServiceQuestionDto> getReadServiceQueList(int serviceQueNum) throws Exception {
		
		List<ServiceQuestionDto> sqlist = svcQueMapper.getReadServiceQueList(serviceQueNum);
		
		for(ServiceQuestionDto sqdto : sqlist) {
			
		}
		
		return sqlist; 
	}
	
	
	@Override
	public Page<ServiceQuestionDto> getServiceQuePaging(int pageNum) {
		
		PageHelper.startPage(pageNum,10);
		return svcQueMapper.getServiceQuePaging(pageNum);
	}

	@Override
	public void updateServiceQue(ServiceQuestionDto sqdto) throws Exception {
		
		svcQueMapper.updateServiceQue(sqdto);
	}

	@Override
	public void deleteServiceQue(int serviceQueNum) throws Exception {
		
		svcQueMapper.deleteServiceQue(serviceQueNum);
	}

	

	

	
	
	
}
