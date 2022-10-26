package com.exe.cozy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    private int odNum;
    private String orderNum;
    private int itemNum;
    private int itemQty;
    private String itemColor;
    private String itemSize;
}
