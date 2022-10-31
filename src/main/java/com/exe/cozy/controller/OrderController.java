package com.exe.cozy.controller;

import com.exe.cozy.domain.*;

import com.exe.cozy.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;


@Controller
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private DeliveryService deliveryService;

    @Resource
    private ItemDetailService itemDetailService;

    @Resource
    private CustomerService customerService;
    @Resource
    private CartService cartService;
    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/order")//order 가면 일단 리스트도 다 떠야함...
    public ModelAndView order(HttpServletRequest request, Principal principal,@ModelAttribute DeliverDto ddto,
                              @ModelAttribute OrderDto odto)  throws Exception {

       int itemNum = Integer.parseInt(request.getParameter("num"));

        ItemDetailDto idto = itemDetailService.getReadItemData(itemNum);
        //바로결제 진행시 수량
       int itemQty = Integer.parseInt((request.getParameter("itemQty")));
       //바로결제 옵션
        String itemSize = request.getParameter("itemSize");
        String itemColor = request.getParameter("itemColor");



        CustomerDto cdto = customerService.getReadData(principal.getName());
        List<DeliverDto> dlist =deliveryService.listDeliver(principal.getName());

        int saleTotalPrice =idto.getItemPrice()-idto.getItemDiscount();
        int preTotalPrice = idto.getItemPrice() *itemQty;
        int salePrice = idto.getItemDiscount() *itemQty;
        int totalPrice = preTotalPrice - salePrice;




        ModelAndView mav = new ModelAndView();
        mav.addObject("dlist",dlist);
        mav.addObject("ddto",ddto);
        mav.addObject("idto",idto);
        mav.addObject("itemNum",itemNum);
        mav.addObject("itemQty",itemQty);
        mav.addObject("saleTotalPrice",saleTotalPrice);
        mav.addObject("preTotalPrice", preTotalPrice);
        mav.addObject("salePrice", salePrice);
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("itemQty",itemQty);
        mav.addObject("cdto",cdto);
        mav.addObject("itemColor",itemColor);
        mav.addObject("itemSize",itemSize);

        mav.setViewName("checkout");
        return mav;}

  @PostMapping("/deliver")
    public ModelAndView deliver(HttpSession session, @ModelAttribute OrderDto odto, @ModelAttribute DeliverDto ddto, Principal principal,HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();

        String itemNum = request.getParameter("itemNum");
        String itemQty = request.getParameter("itemQty");


        String param = "num="+ itemNum +"&itemQty="+itemQty;
        ddto.setCustomerEmail(principal.getName());

        int deliverMaxNum = deliveryService.maxNumDeliver();
        response.setContentType("text/html; charset=UTF-8");
        ddto.setDeliverNum(deliverMaxNum+1); //배송지 번호 증가
        ddto.setDeliverType("추가");
        deliveryService.insertDeliver(ddto);




        mav.setViewName("redirect:order?"+param);

        return mav;
    }
    @PostMapping(value = "/order_ok")
    @ResponseBody
    public ModelAndView order_ok(HttpSession session, @ModelAttribute DeliverDto ddto,
            HttpServletRequest request,Principal principal) throws IOException {
        ModelAndView mav = new ModelAndView();
//제이슨으로 받은 데이터 바꾸기
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        OrderDto odto = objectMapper.readValue(messageBody, OrderDto.class);
        OrderDetailDto oddto = objectMapper.readValue(messageBody, OrderDetailDto.class);


        odto.setCustomerEmail(principal.getName());

        orderService.insertOrder(odto);
        orderDetailService.insertOd(oddto);
        mav.setViewName("redirect:success_order");

        return mav;
    }



    @GetMapping("/cartOrder")//카트주문
    public ModelAndView cartOrder(HttpServletRequest request, Principal principal, @ModelAttribute CartDto cartDto, @ModelAttribute DeliverDto ddto, @ModelAttribute ItemDetailDto itemDetailDto
                                 , @ModelAttribute OrderDto odto)  throws Exception {


        CustomerDto customerDto = customerService.getReadData(principal.getName());
        List<DeliverDto> dlist =deliveryService.listDeliver(principal.getName()); // customerEmail 기반 List 보기
        List<CartDto> clist = cartService.listCart(principal.getName()); //customerEmail 기반 카트보기





        ModelAndView mav = new ModelAndView();

        mav.addObject("dlist",dlist);
        mav.addObject("clist",clist);
        mav.addObject("ddto",ddto);
        mav.addObject("customerDto",customerDto);
        mav.addObject("cartDto",cartDto);

        mav.setViewName("cartOrder");
        return mav;}

    @PostMapping("/cartOrder_ok")
    @ResponseBody
    public ModelAndView cartOrder_ok(HttpSession session,Principal principal, @ModelAttribute DeliverDto ddto,
                                     HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        OrderDto odto = objectMapper.readValue(messageBody, OrderDto.class);
        // 세션 String customerEmail = (String)session.getAttribute("customerEmail");

        /* response.setContentType("text/html; charset=UTF-8");*/


        odto.setCustomerEmail(principal.getName());

        orderService.insertOrder(odto);
        //주문했으면 삭제하기

        mav.setViewName("redirect:success_order");

        return mav;

    }

    @PostMapping("/cartItemOrder_ok")
    @ResponseBody
    public ModelAndView cartItemOrder_ok(HttpSession session,Principal principal, @ModelAttribute DeliverDto ddto,
                                     HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
       OrderDetailDto oddto = objectMapper.readValue(messageBody, OrderDetailDto.class);
        // 세션 String customerEmail = (String)session.getAttribute("customerEmail");
        /* response.setContentType("text/html; charset=UTF-8");*/


        /*int odMaxNum = orderDetailService.odMaxNum();
        oddto.setOdNum(odMaxNum+1);*/

        orderDetailService.insertOd(oddto);
        /*int cartNum = Integer.parseInt(request.getParameter("cartNum"));*/
        /*cartService.deleteCart(cartNum);*/
        mav.setViewName("redirect:success_order");

        return mav;

    }

    @GetMapping("/deleteCart")
    public ModelAndView deleteCart(HttpServletResponse resp, HttpServletRequest req,Principal principal) {


        ModelAndView mav = new ModelAndView();
        cartService.deleteOrderCart(principal.getName());
        mav.setViewName("redirect:success_order");
        return mav;
    }




    @RequestMapping(value = "/success_order")
    @ResponseBody
    public ModelAndView order_success(Principal principal,HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();


        CustomerDto customerDto = customerService.getReadData(principal.getName());
        OrderDto orderNum = orderService.getOrderDetail(principal.getName());


   /*     List<OrderDto> orderDetailList = orderService.getOrderDetailList(principal.getName());*/
        List<OrderDetailDto> odto = orderService.getOrderDetailOne(principal.getName());

/*        mav.addObject("orderDetailList", orderDetailList);*/
        mav.addObject("orderNum", orderNum);


        mav.addObject("odto", odto);


        mav.setViewName("order-success");
        return mav;
    }


}
