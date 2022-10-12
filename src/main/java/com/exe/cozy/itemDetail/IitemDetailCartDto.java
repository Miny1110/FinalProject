package com.exe.cozy.itemDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class IitemDetailCartDto {

    private int cartNum;
    private String customerEmail;
    private int itemNum;
    private int itemQty;
}
