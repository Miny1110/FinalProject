package com.exe.cozy.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.mapper.CartMapper;

@Service("cartService")
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public String test() {
		return cartMapper.test();
	}

}
