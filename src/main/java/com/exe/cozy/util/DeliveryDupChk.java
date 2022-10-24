package com.exe.cozy.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.service.DeliveryService;

@Service
public class DeliveryDupChk {
	
	@Resource DeliveryService deliveryService;

	//중복데이터 검사
	public int dupChk(DeliverDto ddto) {
		
		List<DeliverDto> dList = deliveryService.listDeliver(ddto.getCustomerEmail());
		
		int dup = 0;
		
		for(DeliverDto ddtoChk : dList) {
    		boolean name = ddtoChk.getDeliverName() == null || ddtoChk.getDeliverName().equals(ddto.getDeliverName());
			boolean tel = ddtoChk.getDeliverTel() == null || ddtoChk.getDeliverTel().equals(ddto.getDeliverTel());
			boolean zipcode = ddtoChk.getDeliverZipCode() == null || ddtoChk.getDeliverZipCode().equals(ddto.getDeliverZipCode());
			boolean raddr = ddtoChk.getDeliverRAddr() == null || ddtoChk.getDeliverRAddr().equals(ddto.getDeliverRAddr());
			boolean daddr = ddtoChk.getDeliverDAddr() == null || ddtoChk.getDeliverDAddr().equals(ddto.getDeliverDAddr());
			
			if(name && tel && zipcode && raddr && daddr) { //데이터가 동일하면
				dup++;
			}
    	}
		
		return dup;
	}
	
	//기본배송지 검사
	public int typeChk(List<DeliverDto> dList) {
		
		int typeChk = 0;
		
		for(DeliverDto ddtoChk : dList) {
			boolean type = ddtoChk.getDeliverType().equals("기본");

			if(type) { //기본배송지가 있으면 typeChk!=0 (deliver 테이블 update)
				typeChk++;
			}
		}
		return typeChk;
	}
	
	//기본배송지 수정
	public DeliverDto update(CustomerDto dto) {
		
		DeliverDto ddto = new DeliverDto();
		
		int deliverNum = deliveryService.selectDeliverType("기본");
			
		ddto.setDeliverNum(deliverNum);
		ddto.setDeliverName(dto.getCustomerName());
		ddto.setDeliverRAddr(dto.getCustomerRAddr());
		ddto.setDeliverJAddr(dto.getCustomerJAddr());
		ddto.setDeliverDAddr(dto.getCustomerDAddr());
		ddto.setDeliverZipCode(dto.getCustomerZipCode());
		ddto.setDeliverTel(dto.getCustomerTel());
	
		return ddto;
		
	}
	
	//기본배송지 추가
	public DeliverDto insert(CustomerDto dto) {
		
		DeliverDto ddto = new DeliverDto();
		
		int maxNum = deliveryService.maxNumDeliver();
		
		ddto.setDeliverNum(maxNum+1);
		ddto.setCustomerEmail(dto.getCustomerEmail());
		ddto.setDeliverName(dto.getCustomerName());
		ddto.setDeliverRAddr(dto.getCustomerRAddr());
		ddto.setDeliverJAddr(dto.getCustomerJAddr());
		ddto.setDeliverDAddr(dto.getCustomerDAddr());
		ddto.setDeliverZipCode(dto.getCustomerZipCode());
		ddto.setDeliverTel(dto.getCustomerTel());
		ddto.setDeliverType("기본");
		
		return ddto;
	}
}
