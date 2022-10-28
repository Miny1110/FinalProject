package com.exe.cozy.serviceImpl;

import com.exe.cozy.domain.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.mapper.CartMapper;
import com.exe.cozy.service.CartService;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;


	@Override
	public int cartMaxNum(){
		return cartMapper.cartMaxNum();
	}

	@Override
	public void insertCart(CartDto cdto) {
		cartMapper.insertCart(cdto);
	}

	@Override
	public void deleteCart(int cartNum) {
		cartMapper.deleteCart(cartNum);
	}

	@Override
	public void updateCart(CartDto cdto){
		cartMapper.updateCart(cdto);
	}

	@Override
	public List<CartDto> listCart(String customerEmail) {
		return cartMapper.listCart(customerEmail);
	}

	@Override
	public int checkCart(CartDto cdto){
		return cartMapper.checkCart(cdto);
	}

	@Override
	public void deleteOrderCart(String customerEmail) {
		 cartMapper.deleteOrderCart(customerEmail);
	}
}

