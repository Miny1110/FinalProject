package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ItemQuestionDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.mapper.ItemQuestionMapper;
import com.exe.cozy.service.ItemQuestionService;


@Service
public class ItemQuestionServiceImpl  implements ItemQuestionService{//ItemQuestion
	@Autowired
	private ItemQuestionMapper itemQueMapper;

	@Override
	public int itemQnaMaxNum() throws Exception {
		return itemQueMapper.itemQnaMaxNum();
	}

	@Override
	public void insertItemQna(ItemQuestionDto qdto) throws Exception {
		itemQueMapper.insertItemQna(qdto);
		
	}

	public ItemQuestionDto findItemQna(int itemQueNum) throws Exception {
		return itemQueMapper.findItemQna(itemQueNum);
	}

	@Override
	public List<ItemQuestionDto> getReadItemQnaData(int itemQueNum) throws Exception {
		return itemQueMapper.getReadItemQnaData(itemQueNum);
	}

	@Override
	public void updateItemQna(ItemQuestionDto qdto) throws Exception {
		itemQueMapper.updateItemQna(qdto);
		
	}

	@Override
	public void deleteItemQna(int itemQueNum) throws Exception {
		itemQueMapper.deleteItemQna(itemQueNum);
		
	}

	@Override
	public List<ItemQuestionDto> getReadQnaList(int itemNum) throws Exception {
		List<ItemQuestionDto> list=itemQueMapper.getReadQnaList(itemNum);
//		for(ItemQnaDto dto : list) {
//			
//		
//		System.out.println("aa" + dto.getItemQnaNum());
//		}
		return list;
	}

	
}
