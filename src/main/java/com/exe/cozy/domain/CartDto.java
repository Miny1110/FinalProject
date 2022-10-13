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
private int itemDiscount;
private int itemImage1;
private int itemQty;//수량


    //추가변수
    private int totalPrice; //수량*가격(세일가격)
    private int salePrice; // price-discount

	public void saleTotal(){
       //정률할인
        //this.salePrice =(int) (this.itemPrice * (1-this.itemDiscount));
       //정액할인
        this.salePrice = (int) this.itemPrice - this.itemDiscount;
        this.totalPrice = this.itemPrice*this.itemQty;
    }

}
