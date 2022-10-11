package com.exe.cozy.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @RequestMapping("/cart")
    public String cart(){ return "cart";}

    @RequestMapping("/checkout")
    public String checkout(){ return "checkout";}

    @RequestMapping("/order-success")
    public String order_success(){ return "order-success";}
}
