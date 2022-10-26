package com.exe.cozy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exe.cozy.domain.*;
import com.exe.cozy.service.CartService;
import com.exe.cozy.service.CustomerService;

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

/*@GetMapping("/cart")
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
}*/
@PostMapping("/cart_ok")
public ModelAndView cart_ok(HttpSession session, @ModelAttribute ItemDetailDto idto,@ModelAttribute CartDto cdto, HttpServletRequest request, HttpServletResponse response){
ModelAndView mav = new ModelAndView();

    int itemNum = Integer.parseInt(request.getParameter("num"));
    int itemQty = Integer.parseInt((request.getParameter("itemQty")));
    String param = "itemNum="+ itemNum;
//세션 진행할때 풀기
    // 세션 String customerEmail = (String)session.getAttribute("customerEmail");
    String customerEmail="eunji";
    cartService.insertCart(cdto);
    int cartNum = cartService.cartMaxNum();
    cdto.setCartNum(cartNum+1);
    cdto.setItemNum(itemNum);
    cdto.setItemQty(itemQty);

    mav.setViewName("redirect:itemDetail?"+param);



return mav;

}

@GetMapping("/delete_ok")
    public ModelAndView delete_ok(HttpServletResponse resp, HttpServletRequest req) {

    int cartNum = Integer.parseInt(req.getParameter("cartNum"));

    ModelAndView mav = new ModelAndView();
    cartService.deleteCart(cartNum);
    mav.setViewName("redirect:/cart");
    return mav;
}





}
