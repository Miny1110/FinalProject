package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.mapper.CategoryMapper;
import com.exe.cozy.service.CategoryService;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryMapper CategoryMapper;
	
	@Override
	public int CategoryMaxNum() throws Exception {
		return CategoryMapper.CategoryMaxNum();
	}

	@Override
	public List<ItemDetailDto> selectCategory(String itemMainType, String itemSubType) {
		return CategoryMapper.selectCategory(itemMainType, itemSubType);
	}

	

}
