package com.exe.cozy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDto {


    //cart
private int cartNum;
private int itemNum;

private String customerEmail;
//item
private int itemPrice;
private String itemName;
private String itemSize;
private String itemColor;
private int itemDiscount;
private int itemImage1;
private int itemQty;//수량






}
