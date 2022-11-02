package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.FaqDto;
import com.exe.cozy.mapper.FaqMapper;
import com.exe.cozy.service.FaqService;

@Service
public class FaqServiceImpl implements FaqService{

	@Autowired
	private FaqMapper faqMapper;
	
	
	//create
	@Override
	public int maxFaqNum() throws Exception {
		
		return faqMapper.maxFaqNum();
	}
	
	@Override
	public void insertFaqData(FaqDto fdto) throws Exception {
		
		faqMapper.insertFaqData(fdto);
	}

	
	//article
	@Override
	public FaqDto getReadFaqData(int faqNum) throws Exception {
		
		return faqMapper.getReadFaqData(faqNum);
	}
	
	
	@Override
	public List<FaqDto> getReadFaqList() throws Exception {
		
		return faqMapper.getReadFaqList();
	}
	
	
	@Override
	public void updateFaqData(FaqDto fdto) throws Exception {
		
		faqMapper.updateFaqData(fdto);
	}

	@Override
	public void deleteFaqData(int faqNum) throws Exception {
		
		faqMapper.deleteFaqData(faqNum);
	}

	@Override
	public List<FaqDto> getFaqType1() throws Exception {
		
		return faqMapper.getFaqType1();
	}

	@Override
	public List<FaqDto> getFaqType2() throws Exception {
		
		return faqMapper.getFaqType2();
	}

	@Override
	public List<FaqDto> getFaqType3() throws Exception {
		
		return faqMapper.getFaqType3();
	}
	

	
}
