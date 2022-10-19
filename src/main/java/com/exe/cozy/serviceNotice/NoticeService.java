package com.exe.cozy.serviceNotice;

import java.util.List;

import com.exe.cozy.domain.NoticeDto;

public interface NoticeService {

	public int maxNoticeNum() throws Exception;
	
	public void insertNoticeData(NoticeDto ndto) throws Exception;
	
	public int getNoticeDataCount(String searchKey,String searchValue) throws Exception;
	
	public List<NoticeDto> getNoticeLists(int start,int end,String searchKey,String searchValue) throws Exception;
	
	public NoticeDto getReadNoticeData(int noticeNum) throws Exception;
	
}
