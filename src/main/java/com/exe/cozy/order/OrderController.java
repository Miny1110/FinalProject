package com.exe.cozy.order;

import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.domain.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private DeliverService deliverService;

    @RequestMapping("/order")
    public ModelAndView order(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("checkout.html");
        return mav;}

    @PostMapping("order")
    public ModelAndView order_ok(@ModelAttribute OrderDto odto, @ModelAttribute DeliverDto ddto, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        int orderMaxNum = orderService.OrderMaxNum();
        int deliverMaxNum = deliverService.maxNumDeliver();

        odto.setOrderNum(orderMaxNum +1);
        orderService.insertOrder(odto);

        ddto.setDeliverNum(deliverMaxNum +1);
        deliverService.insertDeliver(ddto);

        mav.setViewName("redirect:cart");

        return mav;

    }



    @RequestMapping("/order-success.*")
    public String order_success(){ return "order-success";}
}
