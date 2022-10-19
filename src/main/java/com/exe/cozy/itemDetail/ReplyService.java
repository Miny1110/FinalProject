package com.exe.cozy.itemDetail;


import java.util.List;

import com.exe.cozy.domain.ReplyDto;

public interface ReplyService {
	
	
	public int replyMaxNum() throws Exception;
	
	
	/*리뷰등록*/
	public void insertReply(ReplyDto rdto)throws Exception;
	
	/*리뷰존재체크*/
	public int checkReply(ReplyDto rdto)throws Exception;
	
	public List<ReplyDto> getReadReplyData(int itemNum) throws Exception;
	
	
}
