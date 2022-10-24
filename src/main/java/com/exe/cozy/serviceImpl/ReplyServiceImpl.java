package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.mapper.ReplyMapper;
import com.exe.cozy.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper replyMapper;

	/* 리뷰등록 */
	@Override
	public void insertReply(ReplyDto rdto) throws Exception {

		replyMapper.insertReply(rdto);

	}

	/* 리뷰목록불러오기 */
	@Override
	public List<ReplyDto> getReadReplyData(int itemNum) throws Exception {
		return replyMapper.getReadReplyData(itemNum);
	}

	/* 리뷰게시글번호 증가 */
	@Override
	public int replyMaxNum() throws Exception {
		return replyMapper.replyMaxNum();
	}

	/* 리뷰수정 */
	@Override
	public void updateReply(ReplyDto rdto) throws Exception {
		replyMapper.updateReply(rdto);

	}

	/* 리뷰replyId값으로 불러오기 */
	@Override
	public ReplyDto findReply(int replyId) throws Exception {
		return replyMapper.findReply(replyId);
	}

	/* 리뷰 삭제 */
	@Override
	public void deleteReply(int replyId) throws Exception {
		replyMapper.deleteReply(replyId);

	}

}
