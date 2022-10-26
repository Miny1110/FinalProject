package com.exe.cozy.service;

import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.OrderDto;

public interface OrderService {
    public int orderMaxNum();
    public void insertOrder(OrderDto odto);
    
    public void updateState(String orderState, String orderNum);
}
