package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.ReplyDto;
import com.github.pagehelper.Page;

@Mapper
public interface CategoryMapper {
	
	 /*카테고리 번호 등록*/
	 public int CategoryMaxNum() throws Exception;
	 
	 /*카테고리 화면 보여주기*/
	 //public List<ItemDetailDto> selectCategory(@Param("itemMainType")String itemMainType, @Param("itemSubType")String itemSubType);
	 public Page<ItemDetailDto> selectCategory(@Param("itemMainType")String itemMainType, @Param("itemSubType")String itemSubType, @Param("pageNum")int pageNum);
	 
	 /*카테고리 총 개수*/
	 public int totalCount(@Param("itemMainType")String itemMainType, @Param("itemSubType")String itemSubType) throws Exception;
}
