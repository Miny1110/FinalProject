package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ItemQuestionDto;
import com.exe.cozy.domain.ItemAnswerDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.mapper.ItemQuestionMapper;
import com.exe.cozy.mapper.ItemAnswerMapper;
import com.exe.cozy.service.ItemAnswerService;


@Service
public class ItemAnswerServiceImpl  implements ItemAnswerService{//ItemAnswer
	@Autowired
	private ItemAnswerMapper itemAnswerMapper;

	@Override
	public int qnaAnswerMaxNum() throws Exception {
		return itemAnswerMapper.qnaAnswerMaxNum();
	}

	@Override
	public void insertQnaAnswer(ItemAnswerDto adto) throws Exception {
		itemAnswerMapper.insertQnaAnswer(adto);
		
	}

	@Override
	public ItemAnswerDto findQnaAnswer(int itemAnsNum) {
		return itemAnswerMapper.findQnaAnswer(itemAnsNum);
	}

	@Override
	public List<ItemAnswerDto> getReadQnaAnswerData(int itemNum) throws Exception {
		return itemAnswerMapper.getReadQnaAnswerData(itemNum);
	}

	@Override
	public void updateQnaAnswer(ItemAnswerDto adto) throws Exception {
		itemAnswerMapper.updateQnaAnswer(adto);
		
	}

	@Override
	public void deleteQnaAnswer(int itemAnsNum) {
		itemAnswerMapper.deleteQnaAnswer(itemAnsNum);
		
	}
	
	
	

	

	
}
