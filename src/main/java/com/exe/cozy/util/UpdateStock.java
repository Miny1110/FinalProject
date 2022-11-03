package com.exe.cozy.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exe.cozy.domain.OrderDetailDto;
import com.exe.cozy.service.ItemDetailService;

@Service
public class UpdateStock {
	
	@Resource ItemDetailService itemDetailService;

	public void updateStock(List<OrderDetailDto> list) throws Exception {
		
		for(OrderDetailDto odto : list) {
			int itemNum = odto.getItemDto().getItemNum();
    		
    		int stock = odto.getItemDto().getItemStock();
    		
    		int qty = odto.getItemQty();

    		int itemStock = stock + qty;
    		odto.getItemDto().setItemStock(itemStock);
    		
    		itemDetailService.updateItemStock(itemNum,itemStock);
    		
    	}
		
		
	}
	
}
