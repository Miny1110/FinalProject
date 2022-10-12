package com.exe.cozy.cart;

import com.exe.cozy.domain.CartDto;
import org.springframework.stereotype.Service;

import com.exe.cozy.mapper.CartMapper;

@Service("cartService")
public class CartServiceImpl implements CartService{

	private final CartMapper cartMapper;

	public CartServiceImpl(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}


	@Override
	public void insertData(CartDto cdto) throws Exception {

	}
}
