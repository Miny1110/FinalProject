package com.exe.cozy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.NoticeDto;
import com.github.pagehelper.Page;

public interface NoticeService {

	public int maxNoticeNum() throws Exception;
	
	public void insertNoticeData(NoticeDto ndto) throws Exception;
	
	public int getNoticeDataCount(String searchNoticeKey,String searchNoticeValue) throws Exception;
	
	//public List<NoticeDto> getNoticeLists(int start,int end,String searchNoticeKey,String searchNoticeValue) throws Exception;
	public Page<NoticeDto> getNoticeLists(@Param("pageNum") int pageNum) throws Exception;
	
	public NoticeDto getReadNoticeData(int noticeNum) throws Exception;
	
	public void updateNoticeData(NoticeDto ndto) throws Exception;
	
	public void deleteNoticeData(int noticeNum) throws Exception;
	
	/* 페이징 */
	//public Page<NoticeDto> getNoticePaging(int pageNum) throws Exception;
	
}
