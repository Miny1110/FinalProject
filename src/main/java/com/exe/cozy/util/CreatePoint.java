package com.exe.cozy.util;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.PointDto;
import com.exe.cozy.service.PointService;

@Service
public class CreatePoint {

	@Resource PointService pointService;
	@Autowired AddDate addDate;
	
	//회원가입 지급 포인트
	public PointDto signUpPoint(String customerEmail) {
		
		PointDto pointDto = new PointDto();
		
		int pointNum = pointService.maxNum();
		pointDto.setPointNum(pointNum+1);
    	pointDto.setPointTitle("회원가입");
    	pointDto.setPointContent("회원가입 축하 포인트");
    	pointDto.setPointAmount(3000);
    	pointDto.setPointState("지급");
    	pointDto.setPointEndDate(addDate.addDate(30));
    	pointDto.setCustomerEmail(customerEmail);
    	
    	return pointDto;
	}
	
	//마이리뷰 삭제 회수 포인트
	public PointDto reviewDelpoint(String customerEmail) {
		
		PointDto pointDto = new PointDto();
		
		int pointNum = pointService.maxNum();
		pointDto.setPointNum(pointNum+1);
    	pointDto.setPointTitle("리뷰삭제");
    	pointDto.setPointContent("리뷰삭제");
    	pointDto.setPointAmount(-500);
    	pointDto.setPointState("소멸");
    	pointDto.setCustomerEmail(customerEmail);
    	
    	return pointDto;
	}
	
	//마이리뷰 삭제 회수 포인트
	public PointDto orderCanclePoint(String customerEmail) {
			
		PointDto pointDto = new PointDto();
		
		int pointNum = pointService.maxNum();
		pointDto.setPointNum(pointNum+1);
	    pointDto.setPointTitle("주문취소");
	    pointDto.setPointContent("주문취소");
	    pointDto.setPointAmount(-1500);
	    pointDto.setPointState("소멸");
	    pointDto.setCustomerEmail(customerEmail);
	    
	    return pointDto;
	}
	
}
