package com.exe.cozy.order;

import com.exe.cozy.cart.CartService;
import com.exe.cozy.customer.CustomerService;
import com.exe.cozy.deliver.DeliveryService;
import com.exe.cozy.domain.*;
import com.exe.cozy.itemDetail.ItemDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @GetMapping ("/order")//order 가면 일단 리스트도 다 떠야함...
    public ModelAndView order(HttpServletRequest request, @ModelAttribute DeliverDto ddto,
                              @ModelAttribute OrderDto odto)  throws Exception {
/*상세페이지 완성되면 이거 풀기*/
//        int itemNum = Integer.parseInt(request.getParameter("itemNum"));
        int itemNum =3;
        ItemDetailDto idto = itemDetailService.getReadItemData(itemNum);


        //바로결제 진행시 수량
        //int itemQty = Integer.parseInt((request.getParameter("itemQty")));
        int itemQty = 100;




        String customerEmail="eunjis";


        CustomerDto cdto = customerService.getReadData(customerEmail);
        List<DeliverDto> dlist =deliveryService.listDeliver(customerEmail);

        int saleTotalPrice =idto.getItemPrice() - idto.getItemDiscount();
        int preTotalPrice = idto.getItemPrice() *itemQty;
        int salePrice = idto.getItemDiscount() *itemQty;
        int totalPrice = preTotalPrice - saleTotalPrice;




        ModelAndView mav = new ModelAndView();
        mav.addObject("dlist",dlist);
        mav.addObject("ddto",ddto);
        mav.addObject("idto",idto);
        mav.addObject("saleTotalPrice",saleTotalPrice);
        mav.addObject("preTotalPrice", preTotalPrice);
        mav.addObject("salePrice", salePrice);
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("itemQty",itemQty);
        mav.addObject("cdto",cdto);

        mav.setViewName("checkout");
        return mav;}

    @PostMapping("/order")
    public ModelAndView order_ok(HttpSession session, @ModelAttribute OrderDto odto, @ModelAttribute DeliverDto ddto, HttpServletRequest request){

     /*   int orderMaxNum = orderService.OrderMaxNum();*/
        //String customerEmail = (String)session.getAttribute("customerEmail");
        String customerEmail = "eunjis";
        int deliverMaxNum = deliveryService.maxNumDeliver();

     /*   odto.setOrderNum(orderMaxNum +1);
        orderService.insertOrder(odto);*/

        ddto.setDeliverNum(deliverMaxNum +1);
        ddto.setDeliverType("추가");
        deliveryService.insertDeliver(ddto);
        ModelAndView mav = new ModelAndView();

        mav.setViewName("redirect:order");

        return mav;
    }

   /* @PostMapping("deliver")
    public ModelAndView deliver(@ModelAttribute OrderDto odto, @ModelAttribute DeliverDto ddto, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        int deliverMaxNum = deliverService.maxNumDeliver();

        ddto.setDeliverNum(deliverMaxNum +1);
        deliverService.insertDeliver(ddto);

        mav.setViewName("redirect:order");

        return mav;
   }*/
   @GetMapping("/cartOrder")//order 가면 일단 리스트도 다 떠야함...
   public ModelAndView cartOrder(HttpServletRequest request, @ModelAttribute DeliverDto ddto,
                             @ModelAttribute OrderDto odto)  throws Exception {
       /*상세페이지 완성되면 이거 풀기*/
//        int itemNum = Integer.parseInt(request.getParameter("itemNum"));
       int itemNum =3;
       ItemDetailDto idto = itemDetailService.getReadItemData(itemNum);


       //바로결제 진행시 수량
       //int itemQty = Integer.parseInt((request.getParameter("itemQty")));
       int itemQty = 100;




       String customerEmail="eunjis";


       CustomerDto cdto = customerService.getReadData(customerEmail);
       List<DeliverDto> dlist =deliveryService.listDeliver(customerEmail);

       int saleTotalPrice =idto.getItemPrice() - idto.getItemDiscount();
       int preTotalPrice = idto.getItemPrice() *itemQty;
       int salePrice = idto.getItemDiscount() *itemQty;
       int totalPrice = preTotalPrice - saleTotalPrice;




       ModelAndView mav = new ModelAndView();
       mav.addObject("dlist",dlist);
       mav.addObject("ddto",ddto);
       mav.addObject("idto",idto);
       mav.addObject("saleTotalPrice",saleTotalPrice);
       mav.addObject("preTotalPrice", preTotalPrice);
       mav.addObject("salePrice", salePrice);
       mav.addObject("totalPrice",totalPrice);
       mav.addObject("itemQty",itemQty);
       mav.addObject("cdto",cdto);

       mav.setViewName("cartOrder");
       return mav;}

    @RequestMapping("/order-success.*")
    public String order_success(){ return "order-success";}


}