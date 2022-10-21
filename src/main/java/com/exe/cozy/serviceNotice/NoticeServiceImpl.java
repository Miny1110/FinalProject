package com.exe.cozy.serviceNotice;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.NoticeDto;
import com.exe.cozy.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;
	
	//create
	@Override
	public int maxNoticeNum() throws Exception {
		
		return noticeMapper.maxNoticeNum();
	}
	
	@Override
	public void insertNoticeData(NoticeDto ndto) throws Exception {
		
		noticeMapper.insertNoticeData(ndto);
	}
	
	
	//list
	@Override
	public int getNoticeDataCount(String searchNoticeKey,String searchNoticeValue) throws Exception{
		
		return noticeMapper.getNoticeDataCount(searchNoticeKey,searchNoticeValue);
	}
	
	@Override
	public List<NoticeDto> getNoticeLists(int start, int end, String searchNoticeKey, String searchNoticeValue) throws Exception{
		
		return noticeMapper.getNoticeLists(start, end, searchNoticeKey, searchNoticeValue);
	}
	
	
	//article
	@Override
	public NoticeDto getReadNoticeData(int noticeNum) throws Exception {
		
		return noticeMapper.getReadNoticeData(noticeNum);
	}

	@Override
	public void updateNoticeData(NoticeDto ndto) throws Exception {
		
		noticeMapper.updateNoticeData(ndto);
	}

	@Override
	public void deleteNoticeData(int noticeNum) throws Exception {
		
		noticeMapper.deleteNoticeData(noticeNum);
	}
	
	
	
	
}
