package com.exe.cozy.mapper;

import com.exe.cozy.domain.DeliverDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryMapper {
    public int maxNumDeliver();
    public void insertDeliver(DeliverDto ddto);

    public List<DeliverDto> listDeliver(String customerEmail);
    
    public void deleteDeliver(int deliverNum);
    
    public void updateDeliver(DeliverDto ddto);
    public int selectDeliverType(String deliverType);
}
