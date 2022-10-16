package com.exe.cozy.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class AddDate {
	
	/* 포인트 endDate 구하는 메소드 */
	public String addDate(int addDate) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
//		cal.add(Calendar.MONTH, 2);
		cal.add(Calendar.DATE, 30);
		
		return df.format(cal.getTime());
	}
	

}
