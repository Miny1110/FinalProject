package com.exe.cozy.itemDetail;

import com.exe.cozy.cart.CartDto;
import com.exe.cozy.cart.CartService;
import com.exe.cozy.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class ItemDetailServiceImpl implements ItemDetailService {
	
	@Autowired
	private CartMapper cartMapper;


	@Override
	public void insertData(CartDto cdto) throws Exception {

	}
}
