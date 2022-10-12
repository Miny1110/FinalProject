package com.exe.cozy.cart;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
//
//	@Resource
    //private CartService cartservice;


//    @RequestMapping("product-bottom-thumbnail.html")
//    public String detail(){ return "product-bottom-thumbnail"; }
//    @RequestMapping("/cart.*")
//    public String cart(){ return "cart";}


    @RequestMapping("/cart.*")
    public String cart() {
        return "cart";
    }
    @RequestMapping("/reviewWrite.*")
    public String reviewWrite() {
        return "reviewWrite";
    }



}
