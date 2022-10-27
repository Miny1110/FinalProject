package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.exe.cozy.domain.ItemQuestionDto;


@Mapper
public interface ItemQuestionMapper {//ItemQuestionMapper

	public int itemQnaMaxNum() throws Exception;

	public void insertItemQna(ItemQuestionDto qdto) throws Exception;

	public ItemQuestionDto findItemQna(int itemQueNum);

	public List<ItemQuestionDto> getReadItemQnaData(int itemNum) throws Exception;

	public void updateItemQna(ItemQuestionDto qdto) throws Exception;
	
	public void deleteItemQna(int itemQueNum);
	
	public  List<ItemQuestionDto> getReadQnaList(int itemNum) throws Exception;
}
