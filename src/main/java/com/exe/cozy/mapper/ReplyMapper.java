package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.ReplyDto;

@Mapper
public interface ReplyMapper {

	public void insertReply(ReplyDto rdto)throws Exception;

	public int checkReply(ReplyDto rdto);
	
	public List<ReplyDto> getReadReplyData(int itemNum) throws Exception;

}
