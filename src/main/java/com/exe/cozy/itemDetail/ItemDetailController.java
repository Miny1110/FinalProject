package com.exe.cozy.itemDetail;

import com.exe.cozy.cart.CartService;
import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

@Controller

public class ItemDetailController {

    @Resource
    private ItemDetailService itemDetailService;

    @Autowired
    MyPage myPage;


    //createItem 창으로 이동하는거
    @GetMapping ("/createItem.*") /** item insert view*/
    public ModelAndView createItem() throws Exception{

        ModelAndView mav = new ModelAndView();
        mav.setViewName("createItem");
        return mav;
    }
    @PostMapping("/createItem_ok")
    public ModelAndView createItem_ok(ItemDetailDto idto, HttpServletRequest request) throws Exception{

        ModelAndView mav = new ModelAndView();
        int itemMaxNum =itemDetailService.itemMaxNum();

        idto.setItemNum(itemMaxNum + 1);
        itemDetailService.insertItem(idto);

        mav.setViewName("redirect:/index");
        return mav;
    }

    @RequestMapping("/itemDetail") /**item 상세페에지 view*/
    public ModelAndView detail(HttpServletRequest request) throws Exception {
        int itemNum = Integer.parseInt(request.getParameter("itemNum"));
        /* detail 페이지 완성되면 이거 풀기
        String pageNum = request.getParameter("pageNum"); */
        /*search 완성되면 이거 풀기
        String searchKey = request.getParameter("searchKey");
        String searchValue = request.getParameter("searchValue");

        if(searchValue!=null && !searchValue.equals("")) {
            searchValue = URLDecoder.decode(searchValue,"UTF-8");} */
        
        //itemDetailService.updateItemHitCount(itemNum);
        
        ItemDetailDto idto = itemDetailService.getReadItemData(itemNum);
        
        if(idto==null){
            ModelAndView mav = new ModelAndView();
            //일단은 index 로 리다이렉트 시키기
            mav.setViewName("redirect:/index");
            /* 상품 리스트 페이지 완성되면 주석 지우기
            mav.setViewName("redirect:/index?pageNum="+pageNum); */
            return mav;

        }
        /*
        String param = "pageNum=" + pageNum;

        if(searchValue!=null&&!searchValue.equals("")) {

            param += "&searchKey=" + searchKey;
            param += "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
        }*/

        int salePrice = idto.getItemPrice() - idto.getItemDiscount();

        System.out.println(salePrice);
        ModelAndView mav = new ModelAndView();

        mav.addObject("idto",idto);
        mav.addObject("salePrice",salePrice);
        //mav.addObject("params",param);
        //mav.addObject("pageNum", pageNum);

        mav.setViewName("itemDetail");
        return mav;

     



    }









}
