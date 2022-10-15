package com.exe.cozy.order;

import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.mapper.DeliverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverServiceImpl implements DeliverService{
    @Autowired
    private DeliverMapper deliverMapper;
    @Override
    public int maxNumDeliver() {
        return deliverMapper.maxNumDeliver();
    }

    @Override
    public void insertDeliver(DeliverDto ddto) {
    deliverMapper.insertDeliver(ddto);
    }
}
