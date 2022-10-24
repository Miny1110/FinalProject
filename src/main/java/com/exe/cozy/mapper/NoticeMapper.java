package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.NoticeDto;

@Mapper
public interface NoticeMapper {
    
	public int maxNoticeNum() throws Exception;
	
	public void insertNoticeData(NoticeDto ndto) throws Exception;
	
	public int getNoticeDataCount(String searchKey,String searchValue) throws Exception;
	
	public List<NoticeDto> getNoticeLists(@Param("start") int start,@Param("end") int end,String searchKey,String searchValue) throws Exception;
	
	public NoticeDto getReadNoticeData(int noticeNum) throws Exception;
	
	public void updateNoticeData(NoticeDto ndto) throws Exception;
	
	public void deleteNoticeData(int noticeNum) throws Exception;
}

