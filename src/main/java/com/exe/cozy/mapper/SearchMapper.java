package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.ItemDetailDto;
import com.github.pagehelper.Page;

@Mapper
public interface SearchMapper {

//	public Page<ItemDetailDto> searchData(String searchValue,int pageNum) throws Exception; 
	public List<ItemDetailDto> searchData(String searchValue) throws Exception; 
	
}
