package com.exe.cozy.service;

import com.exe.cozy.domain.OrderDetailDto;
import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.OrderDto;

import java.util.List;

public interface OrderService {
    public int orderMaxNum();
    public void insertOrder(OrderDto odto);
    
    public void updateCancleState(String orderNum);
    public List<OrderDto> getOrderDetailList(String customerEmail);

    public List<OrderDetailDto> getOrderDetailOne(String customerEmail);

    public OrderDto getOrderDetail(String customerEmail);
}