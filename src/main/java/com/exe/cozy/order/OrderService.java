package com.exe.cozy.order;

import com.exe.cozy.domain.OrderDto;

public interface OrderService {
    public int OrderMaxNum();
    public void insertOrder(OrderDto odto);
}
