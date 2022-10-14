package com.exe.cozy.cart;

import com.exe.cozy.domain.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.mapper.CartMapper;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;


	@Override
	public int cartMaxNum() throws Exception {
		return 0;
	}

	@Override
	public int insertCart(CartDto cdto) throws Exception {
		return 0;
	}

	@Override
	public void deleteCart(int cartNum) throws Exception {

	}

	@Override
	public void updateCart(CartDto cdto) throws Exception {

	}

	@Override
	public List<CartDto> listCart(String customerEmail) throws Exception {
		return null;
	}

	@Override
	public int checkCart(CartDto cdto) throws Exception {
		return 0;
	}
}

