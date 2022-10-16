package com.exe.cozy.itemDetail;

import java.util.List;

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

	@Override
	public int checkReply(ReplyDto rdto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReplyDto> getReadReplyData(int itemNum) throws Exception {
		return replyMapper.getReadReplyData(itemNum);
	}

/*	@Override
	public int checkReply(ReplyDto rdto) throws Exception {
		
		Integer result = replyMapper.checkReply(rdto);
		
		if(result == null) {
			return 0;
		} else {
			return 1;
		}
	}*/

}
