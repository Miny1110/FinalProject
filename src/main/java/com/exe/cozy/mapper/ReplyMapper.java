package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.ReplyDto;

@Mapper
public interface ReplyMapper {

	public int replyMaxNum() throws Exception;

	public void insertReply(ReplyDto rdto) throws Exception;

	public ReplyDto findReply(int replyId);

	public List<ReplyDto> getReadReplyData(int itemNum) throws Exception;

	public void updateReply(ReplyDto rdto) throws Exception;
	
	public void deleteReply(int replyId);

}
