package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.exe.cozy.domain.ItemQnaDto;
import com.exe.cozy.domain.QnaAnswerDto;


@Mapper
public interface QnaAnswerMapper {

	public int qnaAnswerMaxNum() throws Exception;

	public void insertQnaAnswer(QnaAnswerDto adto) throws Exception;

	public QnaAnswerDto findQnaAnswer(int qnaAnswerNum);

	public List<QnaAnswerDto> getReadQnaAnswerData(int itemNum) throws Exception;

	public void updateQnaAnswer(QnaAnswerDto adto) throws Exception;
	
	public void deleteQnaAnswer(int qnaAnswerNum);

}
