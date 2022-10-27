package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.ItemQuestionDto;
import com.exe.cozy.domain.ItemAnswerDto;


public interface ItemAnswerService {//ItemAnswer

	public int qnaAnswerMaxNum() throws Exception;

	public void insertQnaAnswer(ItemAnswerDto adto) throws Exception;

	public ItemAnswerDto findQnaAnswer(int itemAnsNum);

	public List<ItemAnswerDto> getReadQnaAnswerData(int itemNum) throws Exception;

	public void updateQnaAnswer(ItemAnswerDto adto) throws Exception;
	
	public void deleteQnaAnswer(int itemAnsNum);
}
