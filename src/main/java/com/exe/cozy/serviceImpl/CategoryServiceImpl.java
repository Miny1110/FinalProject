package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.mapper.CategoryMapper;
import com.exe.cozy.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryMapper CategoryMapper;
	
	@Override
	public int CategoryMaxNum() throws Exception {
		return CategoryMapper.CategoryMaxNum();
	}

	@Override
	public Page<ItemDetailDto> selectCategory(String itemMainType, String itemSubType, int pageNum) {
		PageHelper.startPage(pageNum, 12);
		return CategoryMapper.selectCategory(itemMainType, itemSubType, pageNum);
	}


	@Override
	public int totalCount(String itemMainType, String itemSubType) throws Exception {
		return CategoryMapper.totalCount(itemMainType, itemSubType);
	}

	

}
