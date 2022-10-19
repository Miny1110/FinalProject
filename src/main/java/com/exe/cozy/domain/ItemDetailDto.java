 package com.exe.cozy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ItemDetailDto {
/**item 상세페에지*/
    private int itemNum;
    private String itemName; //이름
    private String itemMainType; //대분류
    private String itemSubType; //소분류
    private int itemPrice; //가격
    private Integer itemDiscount; //할인가격
    private String itemContent; //상세설명
    private String itemImage1; //큰화면 이미지
    private String itemImage2;
    private String itemImage3;
    private String itemImage4;
    private String itemImage5;
    private String detailImage; //상세페이지 이미지
    private int itemHitcount;
    private int itemStock; //재고수량
    private String itemState; //판매상태여부
    private String todaydeal; //오늘의딜
    /**옵션컬럼*/
    private String itemColor;
    private String itemSize;
}
