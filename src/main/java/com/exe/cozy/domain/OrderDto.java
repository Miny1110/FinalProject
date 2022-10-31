package com.exe.cozy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderDto {
//db에 저장해야하는거
    private String orderNum;
    private String customerEmail;
    private int itemNum;
    private int deliverNum; //배송지
    private int cartNum; // 존재여부 구분
    private int itemQty;
    private String orderState; //주문상태
    private String payment; //결제수단
    private int deliverCost; //배송비
    private String deliverName; //주문자 이름
    private String deliverRAddr;
    private String deliverJAddr;
    private String deliverDAddr;
    private String deliverZipCode;
    private String deliverTel;
    private String deliverMessage;


    private int usePoint;
    private Date orderDate;
    private int itemPrice;
    private int itemDiscount;
    private String itemColor;
    private String itemSize;

    

//땡겨올거
    //item join
    private String itemName;
    private String itemImage1;
/**price랑 discount 도 일단은 땡겨와야함*/

    private int salePrice;
    private int totalPrice;

    public void saleTotal(){
        //정률할인
        //this.salePrice =(int) (this.itemPrice * (1-this.itemDiscount));
        //정액할인
        this.salePrice = (int) this.itemPrice - this.itemDiscount;
        this.totalPrice = this.itemPrice*this.itemQty;
    }

}
