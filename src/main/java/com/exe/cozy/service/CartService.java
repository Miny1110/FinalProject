package com.exe.cozy.service;

import com.exe.cozy.domain.CartDto;

import java.util.List;

public interface CartService {

	public int cartMaxNum();
	public void insertCart(CartDto cdto);
	/**카트삭제*/
	public void deleteCart(int cartNum);
	/**카트수량수정*/
	public void updateCart(CartDto cdto);
	/**카트목록*/
	public List<CartDto> listCart(String customerEmail);
	/**카트확인*/
	public int checkCart(CartDto cdto);

}
