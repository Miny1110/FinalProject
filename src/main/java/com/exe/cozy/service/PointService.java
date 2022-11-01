package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.PointDto;

public interface PointService {
	
	public int maxNum();

	public void insertData(PointDto dto);
	public void insertDelData(PointDto dto);
	
	public List<PointDto> getList(String customerEmail);
	
	public void deleteData(String customerEmail);
	
	public Integer getTotal(String customerEmail);
}
