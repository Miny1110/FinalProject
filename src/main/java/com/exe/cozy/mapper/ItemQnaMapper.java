package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.exe.cozy.domain.ItemQnaDto;


@Mapper
public interface ItemQnaMapper {

	public int itemQnaMaxNum() throws Exception;

	public void insertItemQna(ItemQnaDto qdto) throws Exception;

	public ItemQnaDto findItemQna(int itemQnaNum);

	public List<ItemQnaDto> getReadItemQnaData(int itemNum) throws Exception;

	public void updateItemQna(ItemQnaDto qdto) throws Exception;
	
	public void deleteItemQna(int itemQnaNum);
	
	public  List<ItemQnaDto> getReadQnaList(int itemNum) throws Exception;
}
