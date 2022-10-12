package com.exe.cozy.cart;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	
	@Resource
	private CartService cartservice;

    @RequestMapping("/cart")
    public void test(){ 
    	System.out.println(cartservice.test());
    }


}
