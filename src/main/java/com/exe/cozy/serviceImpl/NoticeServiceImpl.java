package com.exe.cozy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.NoticeDto;
import com.exe.cozy.mapper.NoticeMapper;
import com.exe.cozy.service.NoticeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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
	public Page<NoticeDto> getNoticeLists(int pageNum) throws Exception {
		
		PageHelper.startPage(pageNum,10);
		return noticeMapper.getNoticeLists(pageNum);
	}
	
	/*
	@Override
	public List<NoticeDto> getNoticeLists(int start, int end, String searchNoticeKey, String searchNoticeValue) throws Exception{
		
		return noticeMapper.getNoticeLists(start, end, searchNoticeKey, searchNoticeValue);
	}
	*/
	
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
	/*
	@Override
	public Page<NoticeDto> getNoticePaging(int pageNum) throws Exception {
		
		PageHelper.startPage(pageNum,10);
		return noticeMapper.getNoticePaging(pageNum);
	}

	*/

	
	
	
	
	
	
}
