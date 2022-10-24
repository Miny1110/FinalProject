package com.exe.cozy.serviceImpl;

import com.exe.cozy.domain.OrderDto;
import com.exe.cozy.mapper.OrderMapper;
import com.exe.cozy.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int orderMaxNum() {
        return orderMapper.orderMaxNum();
    }

    @Override
    public void insertOrder(OrderDto odto) {

        orderMapper.insertOrder(odto);
    }
}
