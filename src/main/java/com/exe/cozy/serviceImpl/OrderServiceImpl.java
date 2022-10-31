package com.exe.cozy.serviceImpl;

import com.exe.cozy.domain.OrderDetailDto;
import com.exe.cozy.domain.OrderDto;
import com.exe.cozy.mapper.OrderMapper;
import com.exe.cozy.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	@Override
	public void updateCancleState(String orderNum) {
		orderMapper.updateCancleState(orderNum);
	}

    @Override
    public List<OrderDto> getOrderDetailList(String customerEmail) {
        return orderMapper.getOrderDetailList(customerEmail);
    }

    @Override
    public List<OrderDetailDto> getOrderDetailOne(String customerEmail) {
        return orderMapper.getOrderDetailOne(customerEmail);
    }

    @Override
    public OrderDto getOrderDetail(String customerEmail) {
        return orderMapper.getOrderDetail(customerEmail);
    }


}
