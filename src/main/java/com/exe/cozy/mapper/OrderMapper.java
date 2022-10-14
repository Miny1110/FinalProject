package com.exe.cozy.mapper;

import com.exe.cozy.domain.OrderDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    public int OrderMaxNum();
    public void insertOrder(OrderDto odto) ;

}
