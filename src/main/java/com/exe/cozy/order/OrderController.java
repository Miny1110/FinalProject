package com.exe.cozy.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
    @RequestMapping("/checkout.*")
    public String checkout(){ return "checkout";}

    @RequestMapping("/order-success.*")
    public String order_success(){ return "order-success";}
}
