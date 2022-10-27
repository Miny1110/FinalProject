package com.exe.cozy.util;

import com.exe.cozy.domain.CartDto;
import com.exe.cozy.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartChk {
    @Resource
    CartService cartService;

    public int CtChk(CartDto cdto){

        List<CartDto> clist = cartService.listCart(cdto.getCustomerEmail());

        int cup=0;

        for(CartDto cdtoChk : clist){
            boolean itemNum = cdtoChk.getItemNum() == cdto.getItemNum();

            if(itemNum){
                cup++;
            }
        }
        return cup;
    }
}
