package com.exe.cozy.mapper;

import com.exe.cozy.domain.OrderDetailDto;
import com.exe.cozy.domain.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
public interface OrderMapper {
    public int orderMaxNum();
    public void insertOrder(OrderDto odto) ;

    public void updateCancleState(String orderNum);
    public List<OrderDto> getOrderDetailList(String customerEmail);
    public List<OrderDetailDto> getOrderDetailOne(String customerEmail);

    public OrderDto getOrderDetail(String customerEmail);
}
