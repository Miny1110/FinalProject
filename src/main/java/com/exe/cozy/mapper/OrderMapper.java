package com.exe.cozy.mapper;

import com.exe.cozy.domain.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    public int orderMaxNum();
    public void insertOrder(OrderDto odto) ;
    
    public void updateState(@Param("orderState")String orderState, @Param("orderNum")String orderNum);
}
