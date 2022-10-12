package com.exe.cozy.itemDetail;

import com.exe.cozy.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class ItemDetailController {

    @RequestMapping("/product-bottom-thumbnail.*")
    public String detailItem() {
        return "product-bottom-thumbnail";
    }
    @RequestMapping("/createItem.*")
    public String createItem() {
        return "createItem";
    }
  @RequestMapping("/header.*")
    public String header() {
        return "createItem";
    }




}
