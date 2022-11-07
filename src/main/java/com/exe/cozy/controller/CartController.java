package com.exe.cozy.controller;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;

import com.exe.cozy.service.PointService;
import com.exe.cozy.util.AlertRedirect;
import com.exe.cozy.util.CartChk;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exe.cozy.domain.*;
import com.exe.cozy.service.CartService;
import com.exe.cozy.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
@Resource
private CartService cartService;

@Resource
    CustomerService customerService;
@Resource
PointService pointService;
@Resource
    private ObjectMapper objectMapper = new ObjectMapper();

@PreAuthorize("isAuthenticated")
@GetMapping("/cart")
public ModelAndView cart(HttpServletRequest request,Principal principal,@ModelAttribute CartDto cdto,ItemDetailDto idto){

    CustomerDto customerDto = customerService.getReadData(principal.getName());
    List<CartDto> clist = cartService.listCart(principal.getName());
    Integer totalPoint = pointService.getTotal(principal.getName());


    ModelAndView mav = new ModelAndView();
    mav.addObject("clist",clist);
    mav.addObject("cdto",cdto);
    mav.addObject("idto",idto);
    mav.addObject("customerDto",customerDto);
    mav.addObject("totalPoint",totalPoint);





    mav.setViewName("cart");
    return mav;
}
@PreAuthorize("isAuthenticated")
@PostMapping("/cart_ok")
@ResponseBody
public ModelAndView  cart_ok(HttpSession session,
@ModelAttribute ItemDetailDto idto,Principal principal, HttpServletRequest request,HttpServletResponse response) throws IOException {
ModelAndView mav = new ModelAndView();
//제이슨으로 받은 데이터 바꾸기
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    CartDto cdto = objectMapper.readValue(messageBody, CartDto.class);
//세션 진행할때 풀기
    // 세션 String customerEmail = (String)session.getAttribute("customerEmail");

    int cartNum = cartService.cartMaxNum();

    cdto.setCartNum(cartNum+1);
    cdto.setCustomerEmail(principal.getName());
    cartService.insertCart(cdto);


    List<CartDto> cartList = cartService.listCart(principal.getName());

    session.setAttribute("cartsize",cartList.size());
    session.setAttribute("cartList",cartList);

    mav.setViewName("redirect:cart");
    return mav;

}
//카드에 동일 아이템 있을시 수량 업
@PreAuthorize("isAuthenticated")
@PostMapping("cartUpdate")
@ResponseBody
public ModelAndView  updateCart(HttpSession session, Principal principal,
                             @ModelAttribute ItemDetailDto idto, HttpServletRequest request, HttpServletResponse response) throws IOException {
    ModelAndView mav = new ModelAndView();
//제이슨으로 받은 데이터 바꾸기
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    CartDto cdto = objectMapper.readValue(messageBody, CartDto.class);
//세션 진행할때 풀기
    // 세션 String customerEmail = (String)session.getAttribute("customerEmail");
    List<CartDto> clist = cartService.listCart(principal.getName());



    cdto.setCustomerEmail(principal.getName());
    cartService.updateCart(cdto);
    List<CartDto> cartList = cartService.listCart(principal.getName());

    session.setAttribute("cartsize",cartList.size());
    session.setAttribute("cartList",cartList);
    mav.setViewName("redirect:cart");
    return mav;

}

@PreAuthorize("isAuthenticated")
@GetMapping("/delete_ok")
    public ModelAndView delete_ok(HttpSession session,Principal principal,HttpServletResponse resp, HttpServletRequest req) {

    int cartNum = Integer.parseInt(req.getParameter("cartNum"));

    ModelAndView mav = new ModelAndView();
    cartService.deleteCart(cartNum);

    List<CartDto> cartList = cartService.listCart(principal.getName());

    session.setAttribute("cartsize",cartList.size());
    session.setAttribute("cartList",cartList);
    mav.setViewName("redirect:cart");
    return mav;
}





}
