package com.exe.cozy.order;

import com.exe.cozy.domain.DeliverDto;

import java.util.List;

public interface DeliverService {
    public int maxNumDeliver();
    public void insertDeliver(DeliverDto ddto);
    public List<DeliverDto> listDeliver(String customerEmail);
}
