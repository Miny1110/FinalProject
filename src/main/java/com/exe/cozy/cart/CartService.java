package com.exe.cozy.cart;

import com.exe.cozy.domain.CartDto;

import java.util.List;

public interface CartService {

	public int cartMaxNum() throws Exception;
	public int insertCart(CartDto cdto) throws Exception;
	/**카트삭제*/
	public void deleteCart(int cartNum) throws Exception;
	/**카트수량수정*/
	public void updateCart(CartDto cdto) throws Exception;
	/**카트목록*/
	public List<CartDto> listCart(String customerEmail) throws Exception;
	/**카트확인*/
	public int checkCart(CartDto cdto) throws Exception;

}
