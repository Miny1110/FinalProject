package com.exe.cozy.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeliverDto {
private int deliverNum;
private String customerEmail;
private String deliverType; //이게...기본배송지긴한디...
private String deliverName;
private String deliverAddr1;
private String deliverAddr2;
private String deliverAddrd;
private int deliverTel;
}