package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.ReplyDto;
import com.github.pagehelper.Page;

public interface CategoryService {

	 /*카테고리 번호 등록*/
	 public int CategoryMaxNum() throws Exception;
	 
	 /*카테고리 화면 보여주기*/
	 //public List<ItemDetailDto> selectCategory(String itemMainType, String itemSubType);
	 public Page<ItemDetailDto> selectCategory(String itemMainType, String itemSubType, int pageNum);
	 
	 
//		public List<ReplyDto> getReviewList(String customerEmail);
//		public Page<ReplyDto> getReviewPaging(String customerEmail, int pageNum);
	 
}
