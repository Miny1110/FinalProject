package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.mapper.SearchMapper;
import com.exe.cozy.service.SearchService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
	
	private final SearchMapper searchMapper;

	@Override
	public List<ItemDetailDto> searchData(String searchValue) throws Exception {
		List<ItemDetailDto> searchlist = searchMapper.searchData(searchValue);
		return searchMapper.searchData(searchValue);
	}
	
	/*
	@Override
	public Page<ItemDetailDto> searchData(String searchValue, int pageNum) throws Exception {
		Page<ItemDetailDto> searchlist = searchMapper.searchData(searchValue, pageNum);
		PageHelper.startPage(pageNum, 10);
		return searchMapper.searchData(searchValue, pageNum);

	}
	*/
}
