package com.exe.cozy.mapper;

import com.exe.cozy.domain.OrderDetailDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
   public int odMaxNum();

   public void insertOd(OrderDetailDto oddto) ;

}
