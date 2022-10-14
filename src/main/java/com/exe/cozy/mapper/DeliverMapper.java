package com.exe.cozy.mapper;

import com.exe.cozy.domain.DeliverDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliverMapper {
    public int maxNumDeliver();
    public void insertDeliver(DeliverDto ddto);
}
