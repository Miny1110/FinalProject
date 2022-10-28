package com.exe.cozy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    private String odNum;
    private String orderNum;
    private int itemNum;
    private int itemQty;
    private String itemColor;
    private String itemSize;
    
    //join에 필요
    private OrderDto orderDto;
    private ItemDetailDto itemDto;
}
