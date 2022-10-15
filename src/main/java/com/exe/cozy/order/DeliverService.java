package com.exe.cozy.order;

import com.exe.cozy.domain.DeliverDto;

public interface DeliverService {
    public int maxNumDeliver();
    public void insertDeliver(DeliverDto ddto);
}
