package com.exe.cozy.itemDetail;

import com.exe.cozy.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class ItemDetailController {
	
	@Resource
	private CartService cartservice;


//    @RequestMapping("product-bottom-thumbnail.html")
//    public String detail(){ return "product-bottom-thumbnail"; }
//    @RequestMapping("/cart.*")
//    public String cart(){ return "cart";}

    
    @RequestMapping("/cart")
    public void test(){ 
    	System.out.println(cartservice.test());
    }


}
