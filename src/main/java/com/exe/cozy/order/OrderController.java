package com.exe.cozy.order;

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
    @GetMapping("/order")
    public ModelAndView order(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("checkout");
        return mav;}

    @PostMapping("order")
    public ModelAndView order_ok(@ModelAttribute OrderDto odto, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        int orderMaxNum = orderService.OrderMaxNum();

        odto.setOrderNum(orderMaxNum +1);
        orderService.insertOrder(odto);

        mav.setViewName("redirect:cart");

        return mav;


    }

    @RequestMapping("/order-success.*")
    public String order_success(){ return "order-success";}
}
