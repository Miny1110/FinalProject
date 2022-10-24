package com.exe.cozy.service;

import com.exe.cozy.domain.OrderDto;

public interface OrderService {
    public int OrderMaxNum();
    public void insertOrder(OrderDto odto);
}
