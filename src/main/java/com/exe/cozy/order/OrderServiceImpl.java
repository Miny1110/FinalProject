package com.exe.cozy.order;

import com.exe.cozy.domain.OrderDto;
import com.exe.cozy.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int OrderMaxNum() {
        return orderMapper.OrderMaxNum();
    }

    @Override
    public void insertOrder(OrderDto odto) {

        orderMapper.insertOrder(odto);
    }
}
