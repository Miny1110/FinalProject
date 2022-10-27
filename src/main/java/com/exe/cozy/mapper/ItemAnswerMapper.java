package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.exe.cozy.domain.ItemQuestionDto;
import com.exe.cozy.domain.ItemAnswerDto;


@Mapper
public interface ItemAnswerMapper {//ItemAnswer

	public int qnaAnswerMaxNum() throws Exception;

	public void insertQnaAnswer(ItemAnswerDto adto) throws Exception;

	public ItemAnswerDto findQnaAnswer(int itemAnsNum);

	public List<ItemAnswerDto> getReadQnaAnswerData(int itemNum) throws Exception;

	public void updateQnaAnswer(ItemAnswerDto adto) throws Exception;
	
	public void deleteQnaAnswer(int itemAnsNum);

}
