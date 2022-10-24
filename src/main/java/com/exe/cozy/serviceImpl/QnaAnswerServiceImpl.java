package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ItemQnaDto;
import com.exe.cozy.domain.QnaAnswerDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.mapper.ItemQnaMapper;
import com.exe.cozy.mapper.QnaAnswerMapper;
import com.exe.cozy.service.QnaAnswerService;


@Service
public class QnaAnswerServiceImpl  implements QnaAnswerService{
	@Autowired
	private QnaAnswerMapper qnaAnswerMapper;

	@Override
	public int qnaAnswerMaxNum() throws Exception {
		return qnaAnswerMapper.qnaAnswerMaxNum();
	}

	@Override
	public void insertQnaAnswer(QnaAnswerDto adto) throws Exception {
		qnaAnswerMapper.insertQnaAnswer(adto);
		
	}

	@Override
	public QnaAnswerDto findQnaAnswer(int qnaAnswerNum) {
		return qnaAnswerMapper.findQnaAnswer(qnaAnswerNum);
	}

	@Override
	public List<QnaAnswerDto> getReadQnaAnswerData(int itemNum) throws Exception {
		return qnaAnswerMapper.getReadQnaAnswerData(itemNum);
	}

	@Override
	public void updateQnaAnswer(QnaAnswerDto adto) throws Exception {
		qnaAnswerMapper.updateQnaAnswer(adto);
		
	}

	@Override
	public void deleteQnaAnswer(int qnaAnswerNum) {
		qnaAnswerMapper.deleteQnaAnswer(qnaAnswerNum);
		
	}
	
	
	

	

	
}
