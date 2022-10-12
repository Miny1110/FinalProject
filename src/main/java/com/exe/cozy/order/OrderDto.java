package com.exe.cozy.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private int orderNum;
    private String customerEmail;
    private int itemNum;
    private int itemQty;
    private int cartNum;
    private String orderState;
    private String payment;

}
