package com.exe.cozy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.ItemDetailDto;

@Mapper
public interface CategoryMapper {
	
	 /*카테고리 번호 등록*/
	 public int CategoryMaxNum() throws Exception;
	 
	 /*카테고리 화면 보여주기*/
	 public ItemDetailDto selectCategory(@Param("itemMainType")String itemMainType, @Param("itemSubType")String itemSubType);
	
}
