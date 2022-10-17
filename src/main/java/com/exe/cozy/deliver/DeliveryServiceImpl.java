package com.exe.cozy.deliver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.mapper.DeliverMapper;

@Service
public class DeliveryServiceImpl implements DeliveryService{

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

	@Override
	public List<DeliverDto> listDeliver(String customerEmail) {
		return deliverMapper.listDeliver(customerEmail);
	}

	@Override
	public void deleteDeliver(int deliverNum) {
		deliverMapper.deleteDeliver(deliverNum);
	}

}
