package com.exe.cozy.deliver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.DeliverDto;
import com.exe.cozy.mapper.DeliveryMapper;

@Service
public class DeliveryServiceImpl implements DeliveryService{

	@Autowired private DeliveryMapper deliveryMapper;
	
	@Override
	public int maxNumDeliver() {
		return deliveryMapper.maxNumDeliver();
	}

	@Override
	public int insertDeliver(DeliverDto ddto) {
		int dup = dupChk(ddto);
		
		if(dup==0) {
			int maxNum = maxNumDeliver();
			ddto.setDeliverNum(maxNum+1);
			ddto.setDeliverType("추가");
			ddto.setCustomerEmail("wjdalswjd453@naver.com");
			deliveryMapper.insertDeliver(ddto);
		}
		return dup;
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
	public int updateDeliver(DeliverDto ddto) {
		int dup = dupChk(ddto);
		
		if(dup==0) {
			int maxNum = maxNumDeliver();
			ddto.setDeliverNum(maxNum+1);
			deliveryMapper.updateDeliver(ddto);
		}
		return dup;
	}

	@Override
	public int selectDeliverType(String deliverType) {
		return deliveryMapper.selectDeliverType(deliverType);
	}

//일반메소드-----------------------------------------------------------------
	
	//중복데이터 체크
	private int dupChk(DeliverDto ddto) {
				
		String customerEmail = "wjdalswjd453@naver.com";
		
		List<DeliverDto> dList = listDeliver(customerEmail);
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
}
