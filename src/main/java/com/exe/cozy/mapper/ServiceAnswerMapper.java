package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.ServiceAnswerDto;
import com.exe.cozy.domain.ServiceQuestionDto;

@Mapper
public interface ServiceAnswerMapper {
	
	/* 새 답변 번호 */
	public int serviceAnsMaxNum() throws Exception;
	
	/* 답변 등록 */
	public void insertServiceAns(ServiceAnswerDto sadto) throws Exception;
	
	/* 답변 게시글 번호로 불러오기*/
	public ServiceAnswerDto findServiceAns(int serviceAnsNum) throws Exception;
	
	/* 답변 불러오기*/
	public ServiceAnswerDto getReadServiceAnsData(int serviceAnsNum) throws Exception;
	
	/* 리스트로 답변 불러오기*/
	public List<ServiceAnswerDto> getReadServiceAnsDatas(int serviceAnsNum) throws Exception;
	
	/* 답변 수정*/
	public void updateServiceAns(ServiceAnswerDto sadto) throws Exception;
	
	/* 문의 삭제*/
	public void deleteServiceAns(int serviceAnsNum) throws Exception;

}
