package com.exe.cozy.itemDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper replyMapper;

	/*리뷰등록*/
	@Override
	public void insertReply(ReplyDto rdto) throws Exception {
		replyMapper.insertReply(rdto);
		
	}

}
