package com.exe.cozy.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @RequestMapping("product-bottom-thumbnail.html")
    public String detail(){ return "product-bottom-thumbnail"; }
    @RequestMapping("/cart.*")
    public String cart(){ return "cart";}


}
