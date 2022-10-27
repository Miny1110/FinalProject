package com.exe.cozy.service;

import java.util.List;

import com.exe.cozy.domain.ItemQuestionDto;


public interface ItemQuestionService {//ItemQuestion

	public int itemQnaMaxNum() throws Exception;

	/* 문의등록 */
	public void insertItemQna(ItemQuestionDto qdto) throws Exception;

	/* 문의게시글번호로 불러오기 */
	public ItemQuestionDto findItemQna(int itemQueNum) throws Exception;

	/* 문의목록불러오기 */
	public List<ItemQuestionDto> getReadItemQnaData(int itemQueNum) throws Exception;

	/* 문의수정 */
	public void updateItemQna(ItemQuestionDto qdto) throws Exception;
	
	/*문의삭제*/
	public void deleteItemQna(int itemQueNum) throws Exception;

	
	public List<ItemQuestionDto> getReadQnaList(int itemNum) throws Exception;

}
