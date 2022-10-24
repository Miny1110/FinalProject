package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.ItemQnaDto;
import com.exe.cozy.domain.QnaAnswerDto;


public interface QnaAnswerService {

	public int qnaAnswerMaxNum() throws Exception;

	public void insertQnaAnswer(QnaAnswerDto adto) throws Exception;

	public QnaAnswerDto findQnaAnswer(int qnaAnswerNum);

	public List<QnaAnswerDto> getReadQnaAnswerData(int itemNum) throws Exception;

	public void updateQnaAnswer(QnaAnswerDto adto) throws Exception;
	
	public void deleteQnaAnswer(int qnaAnswerNum);
}
