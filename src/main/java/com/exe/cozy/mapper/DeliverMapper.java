package com.exe.cozy.mapper;

import com.exe.cozy.domain.DeliverDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliverMapper {
    public int maxNumDeliver();
    public void insertDeliver(DeliverDto ddto);

    public List<DeliverDto> listDeliver(String customerEmail);
}
