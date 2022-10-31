package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ItemDetailDto;

import com.exe.cozy.mapper.MainHomeMapper;

import com.exe.cozy.service.MainHomeService;

@Service
public class MainHomeServiceImpl implements MainHomeService{
	
	@Autowired
	private MainHomeMapper mainHomemapper;

	@Override
	public List<ItemDetailDto> selectTodaydeal() throws Exception {
		return mainHomemapper.selectTodaydeal();
	}

	@Override
	public List<ItemDetailDto> selectHitcount() throws Exception {
		return mainHomemapper.selectHitcount();
	}

	@Override
	public List<ItemDetailDto> selectNewItem() throws Exception {
		return mainHomemapper.selectNewItem();
	}

	

	

	

}
