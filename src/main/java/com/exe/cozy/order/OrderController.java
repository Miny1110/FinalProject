package com.exe.cozy.order;

import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.OrderDto;
import com.exe.cozy.itemDetail.ItemDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private DeliverService deliverService;

    @Resource
    private ItemDetailService itemDetailService;

    @RequestMapping("/order")//order 가면 일단 리스트도 다 떠야함...
    public ModelAndView order(HttpServletRequest request, @ModelAttribute DeliverDto ddto,
                              @ModelAttribute OrderDto odto) throws Exception {
/*상세페이지 완성되면 이거 풀기*/
//        int itemNum = Integer.parseInt(request.getParameter("itemNum"));
        int itemNum =3;
        ItemDetailDto idto = itemDetailService.getReadItemData(itemNum);

        //바로결제 진행시 수량
        //int itemQty = Integer.parseInt((request.getParameter("itemQty")));
        int itemQty = 100;




        String customerEmail="eunji";
        List<DeliverDto> dlist =deliverService.listDeliver(customerEmail);

        int salePrice = idto.getItemPrice() - idto.getItemDiscount();
        int totalPrice = idto.getItemPrice() *itemQty;



        ModelAndView mav = new ModelAndView();
        mav.addObject("dlist",dlist);
        mav.addObject("ddto",ddto);
        mav.addObject("idto",idto);
        mav.addObject("salePrice",salePrice);
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("itemQty",itemQty);


        mav.setViewName("checkout");
        return mav;}

    @PostMapping("/order")
    public ModelAndView order_ok(@ModelAttribute OrderDto odto, @ModelAttribute DeliverDto ddto, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        int orderMaxNum = orderService.OrderMaxNum();
        int deliverMaxNum = deliverService.maxNumDeliver();

        odto.setOrderNum(orderMaxNum +1);
        orderService.insertOrder(odto);

        mav.setViewName("redirect:cart");

        return mav;
    }
    @GetMapping("/deliver")
    public ModelAndView deliver(HttpServletRequest request, @ModelAttribute DeliverDto ddto,
                                @ModelAttribute OrderDto odto) throws Exception {


        int itemNum =3;
        ItemDetailDto idto = itemDetailService.getReadItemData(itemNum);

        //바로결제 진행시 수량
        //int itemQty = Integer.parseInt((request.getParameter("itemQty")));
        int itemQty = 100;




        String customerEmail="eunji";
        List<DeliverDto> dlist =deliverService.listDeliver(customerEmail);

        int salePrice = idto.getItemPrice() - idto.getItemDiscount();
        int totalPrice = idto.getItemPrice() *itemQty;




        ModelAndView mav = new ModelAndView();
        mav.addObject("dlist",dlist);
        mav.addObject("ddto",ddto);
        mav.addObject("idto",idto);
        mav.addObject("salePrice",salePrice);
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("itemQty",itemQty);

        mav.setViewName("deliver");
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

    @RequestMapping("/order-success.*")
    public String order_success(){ return "order-success";}
}
