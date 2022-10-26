package com.exe.cozy.serviceImpl;

import com.exe.cozy.domain.OrderDetailDto;
import com.exe.cozy.mapper.OrderDetailMapper;
import com.exe.cozy.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public int odMaxNum() {
        return orderDetailMapper.odMaxNum();
    }

    @Override
    public void insertOd(OrderDetailDto oddto) {
        orderDetailMapper.insertOd(oddto);
    }
}
