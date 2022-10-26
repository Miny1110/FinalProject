package com.exe.cozy.service;

import com.exe.cozy.domain.OrderDetailDto;

public interface OrderDetailService {
    public int odMaxNum();
    public void insertOd(OrderDetailDto oddto) ;
}
