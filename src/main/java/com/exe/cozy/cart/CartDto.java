package com.exe.cozy.cart;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class CartDto {

    private int cartNum;
    private String customerEmail;
    private int itemNum;
    private int itemQty;
}
