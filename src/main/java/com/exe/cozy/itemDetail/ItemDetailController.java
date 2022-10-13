package com.exe.cozy.itemDetail;

import com.exe.cozy.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class ItemDetailController {

    @RequestMapping("/product-bottom-thumbnail.*") /**item 상세페에지 view*/
    public String detailItem() {
        return "product-bottom-thumbnail";
    }
    @PostMapping ("/createItem.*") /** item insert view*/
    public String createItem() {
        return "createItem";
    }






}
