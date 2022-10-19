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
	
	public int getNoticeDataCount(@RequestParam("searchKey") String searchKey,@RequestParam("searchValue") String searchValue) throws Exception;
	
	public List<NoticeDto> getNoticeLists(int start,int end,String searchKey,String searchValue) throws Exception;
	
	public NoticeDto getReadNoticeData(int noticeNum) throws Exception;
	
}

