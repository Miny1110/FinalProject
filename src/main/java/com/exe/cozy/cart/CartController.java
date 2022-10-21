package com.exe.cozy.cart;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exe.cozy.customer.CustomerService;
import com.exe.cozy.domain.CartDto;
import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.ItemDetailDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {
@Resource
private CartService cartService;

@Resource
    CustomerService customerService;

@GetMapping("/cart")
public ModelAndView cart(HttpServletRequest request,@ModelAttribute CartDto cdto,ItemDetailDto idto){

    String customerEmail = "eunjis";

    CustomerDto customerDto = customerService.getReadData(customerEmail);
    List<CartDto> clist = cartService.listCart(customerEmail);


    ModelAndView mav = new ModelAndView();
    mav.addObject("clist",clist);
    mav.addObject("cdto",cdto);
    mav.addObject("idto",idto);
    mav.addObject("customerDto",customerDto);





    mav.setViewName("cart");
    return mav;
}

@GetMapping("delete_ok")
    public ModelAndView delete_ok(HttpServletResponse resp, HttpServletRequest req) {

    int cartNum = Integer.parseInt(req.getParameter("cartNum"));

    ModelAndView mav = new ModelAndView();
    cartService.deleteCart(cartNum);
    mav.setViewName("redirect:/cart");
    return mav;
}





}
