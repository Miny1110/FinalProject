package com.exe.cozy.customer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
	
	@Resource
	private CustomerService customerService;


//    @RequestMapping("product-bottom-thumbnail.html")
//    public String detail(){ return "product-bottom-thumbnail"; }
//    @RequestMapping("/cart.*")
//    public String cart(){ return "cart";}

    
    @RequestMapping("/customer")
    public String test(){ 
    	System.out.println(customerService.test());
    	
    	return "user-dashboard";
    }


}
