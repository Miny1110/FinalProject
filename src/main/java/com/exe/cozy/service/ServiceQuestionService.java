package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.ServiceQuestionDto;
import com.github.pagehelper.Page;



public interface ServiceQuestionService {

	public int serviceQueMaxNum() throws Exception;

	/* 문의등록 */
	public void insertServiceQue(ServiceQuestionDto sqdto) throws Exception;
	
	/* 문의게시글번호로 불러오기 */
	public ServiceQuestionDto findServiceQue1(int serviceQueNum) throws Exception;
	
	public ServiceQuestionDto findServiceQue(int serviceQueNum) throws Exception;
	
	
	/* 문의목록불러오기 */
	public ServiceQuestionDto getReadServiceQueData(int serviceQueNum) throws Exception;

	public List<ServiceQuestionDto> getReadServiceQueList(int serviceQueNum) throws Exception;
	
	/* 페이징 */
	public Page<ServiceQuestionDto> getServiceQuePaging(int pageNum) throws Exception;
	
	/* 문의수정 */
	public void updateServiceQue(ServiceQuestionDto sqdto) throws Exception;
	
	/*문의삭제*/
	public void deleteServiceQue(int serviceQueNum) throws Exception;

	
}
