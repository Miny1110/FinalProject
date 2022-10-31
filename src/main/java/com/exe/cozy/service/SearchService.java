package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.ItemDetailDto;
import com.github.pagehelper.Page;

public interface SearchService {
//	public Page<ItemDetailDto> searchData(String searchValue, int pageNum) throws Exception; 
	public List<ItemDetailDto> searchData(String searchValue) throws Exception;
}
