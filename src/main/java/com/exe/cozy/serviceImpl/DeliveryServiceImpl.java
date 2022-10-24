package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.mapper.DeliveryMapper;
import com.exe.cozy.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService{

	@Autowired private DeliveryMapper deliveryMapper;
	
	@Override
	public int maxNumDeliver() {
		return deliveryMapper.maxNumDeliver();
	}

	@Override
	public void insertDeliver(DeliverDto ddto) {
		deliveryMapper.insertDeliver(ddto);
	}

	@Override
	public List<DeliverDto> listDeliver(String customerEmail) {
		return deliveryMapper.listDeliver(customerEmail);
	}

	@Override
	public void deleteDeliver(int deliverNum) {
		deliveryMapper.deleteDeliver(deliverNum);
	}
	
	@Override
	public void updateDeliver(DeliverDto ddto) {
		deliveryMapper.updateDeliver(ddto);
	}

	@Override
	public int selectDeliverType(String deliverType) {
		return deliveryMapper.selectDeliverType(deliverType);
	}

}
