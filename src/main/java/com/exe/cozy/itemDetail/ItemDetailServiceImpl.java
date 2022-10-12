package com.exe.cozy.itemDetail;

import com.exe.cozy.mapper.ItemDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemDetailService")
public class ItemDetailServiceImpl implements ItemDetailService {
	
	@Autowired
	private ItemDetailMapper itemDetailMapper;


}
