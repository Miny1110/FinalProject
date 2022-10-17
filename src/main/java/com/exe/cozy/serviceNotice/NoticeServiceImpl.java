package com.exe.cozy.serviceNotice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.NoticeDto;
import com.exe.cozy.mapper.NoticeMapper;

@Service("serviceNotice")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDto> getNoticeList() {
		
		return noticeMapper.getNoticeList();
	}

}
