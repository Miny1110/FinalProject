package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.ItemDetailDto;

@Mapper
public interface MainHomeMapper {
	
	/*오늘의 딜 제품 출력*/
	public List<ItemDetailDto> selectTodaydeal() throws Exception;
	
	/*인기상품(hitCount) 순으로 출력*/
	public List<ItemDetailDto> selectHitcount() throws Exception;
	
	/*신상품 순으로 출력*/
	public List<ItemDetailDto> selectNewItem() throws Exception;
	 
}
