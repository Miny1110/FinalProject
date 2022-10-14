package com.exe.cozy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.ReplyDto;

@Mapper
public interface ReplyMapper {

	public void insertReply(ReplyDto rdto)throws Exception;
	

}
