package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.exe.cozy.domain.NoticeDto;
import com.github.pagehelper.Page;

@Mapper
public interface NoticeMapper {
    
	/* 공지 번호*/
	public int maxNoticeNum() throws Exception;
	
	/* 공지 등록*/
	public void insertNoticeData(NoticeDto ndto) throws Exception;
	
	public int getNoticeDataCount(@Param("searchNoticeKey") String searchNoticeKey, @Param("searchNoticeValue") String searchNoticeValue) throws Exception;
	
	public Page<NoticeDto> getNoticeLists(@Param("pageNum") int pageNum) throws Exception;
	
	public NoticeDto getReadNoticeData(int noticeNum) throws Exception;
	
	public void updateNoticeData(NoticeDto ndto) throws Exception;
	
	public void deleteNoticeData(int noticeNum) throws Exception;
	
	
}

