package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.exe.cozy.domain.NoticeDto;

@Mapper
public interface NoticeMapper {
    
	public int maxNoticeNum() throws Exception;
	
	public void insertNoticeData(NoticeDto ndto) throws Exception;
	
	public int getNoticeDataCount(@Param("searchNoticeKey") String searchNoticeKey, @Param("searchNoticeValue") String searchNoticeValue) throws Exception;
	
	public List<NoticeDto> getNoticeLists(@Param("start") int start,@Param("end") int end,String searchNoticeKey,String searchNoticeValue) throws Exception;
	
	public NoticeDto getReadNoticeData(int noticeNum) throws Exception;
	
	public void updateNoticeData(NoticeDto ndto) throws Exception;
	
	public void deleteNoticeData(int noticeNum) throws Exception;
}

