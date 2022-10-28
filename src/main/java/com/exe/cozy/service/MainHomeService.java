package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.ItemDetailDto;

public interface MainHomeService {

	/*오늘의 딜 제품 출력*/
	public List<ItemDetailDto> selectTodaydeal() throws Exception;
	
	
	
	 
}
