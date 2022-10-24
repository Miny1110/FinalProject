package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.ReplyDto;

public interface ReplyService {

	public int replyMaxNum() throws Exception;

	/* 리뷰등록 */
	public void insertReply(ReplyDto rdto) throws Exception;

	/* 리뷰게시글번호로 불러오기 */
	public ReplyDto findReply(int replyId) throws Exception;

	/* 리뷰목록불러오기 */
	public List<ReplyDto> getReadReplyData(int itemNum) throws Exception;

	/* 리뷰수정 */
	public void updateReply(ReplyDto rdto) throws Exception;
	
	/*리뷰삭제*/
	public void deleteReply(int replyId) throws Exception;
}
