package com.exe.cozy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.NoticeDto;




@Mapper
public interface NoticeMapper {
    
	//게시판 목록 가져오기
	public List<NoticeDto> getNoticeList();
}

