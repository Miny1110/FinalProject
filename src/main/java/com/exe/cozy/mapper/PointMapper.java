package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.PointDto;

@Mapper
public interface PointMapper {

	public int maxNum();
	
	public void insertData(PointDto dto);
	public void insertDelData(PointDto dto);
	
	public List<PointDto> getList(String customerEmail);
	
	public void deleteData(String customerEmail);

	public int getTotal(String customerEmail);
}
