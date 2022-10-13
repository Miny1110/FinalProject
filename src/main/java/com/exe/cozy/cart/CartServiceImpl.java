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
		return cartMapper.cartMaxNum();
	}

	@Override
	public int insertCart(CartDto cdto) throws Exception {
		int checkcart = cartMapper.checkCart(cdto);
		if(checkcart !=null){
			return 2;
		}
		try {
			return cartMapper.insertCart(cdto);

		}catch (Exception e){
			return 0;
		}

		cartMapper.insertCart(cdto); ;
	}

	@Override
	public void deleteCart(int cartNum) throws Exception {
		cartMapper.deleteCart(cartNum);
	}

	@Override
	public void updateCart(CartDto cdto) throws Exception {
		cartMapper.updateCart(cdto);
	}

	@Override
	public List<CartDto> listCart(String customerEmail) throws Exception {
		return cartMapper.listCart(customerEmail);
	}

	@Override
	public int checkCart(CartDto cdto) throws Exception {
		CartDto checkdto = cartMapper.checkCart(cdto);
		return cartMapper.checkCart(cdto);
	}
}

