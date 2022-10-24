package com.exe.cozy.itemDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.ItemQnaDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.mapper.ItemQnaMapper;


@Service
public class ItemQnaServiceImpl  implements ItemQnaService{
	@Autowired
	private ItemQnaMapper itemQnaMapper;

	@Override
	public int itemQnaMaxNum() throws Exception {
		return itemQnaMapper.itemQnaMaxNum();
	}

	@Override
	public void insertItemQna(ItemQnaDto qdto) throws Exception {
		itemQnaMapper.insertItemQna(qdto);
		
	}

	public ItemQnaDto findItemQna(int itemQnaNum) throws Exception {
		return itemQnaMapper.findItemQna(itemQnaNum);
	}

	@Override
	public List<ItemQnaDto> getReadItemQnaData(int itemQnaNum) throws Exception {
		return itemQnaMapper.getReadItemQnaData(itemQnaNum);
	}

	@Override
	public void updateItemQna(ItemQnaDto qdto) throws Exception {
		itemQnaMapper.updateItemQna(qdto);
		
	}

	@Override
	public void deleteItemQna(int itemQnaNum) throws Exception {
		itemQnaMapper.deleteItemQna(itemQnaNum);
		
	}

	@Override
	public List<ItemQnaDto> getReadQnaList(int itemNum) throws Exception {
		List<ItemQnaDto> list=itemQnaMapper.getReadQnaList(itemNum);
		for(ItemQnaDto dto : list) {
			
		
		System.out.println("aa" + dto.getItemQnaNum());
		}
		return list;
	}

	
}
