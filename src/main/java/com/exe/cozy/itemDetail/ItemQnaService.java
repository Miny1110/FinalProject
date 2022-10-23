package com.exe.cozy.itemDetail;

import java.util.List;

import com.exe.cozy.domain.ItemQnaDto;


public interface ItemQnaService {

	public int itemQnaMaxNum() throws Exception;

	/* 문의등록 */
	public void insertItemQna(ItemQnaDto qdto) throws Exception;

	/* 문의게시글번호로 불러오기 */
	public ItemQnaDto findItemQna(int itemQnaNum) throws Exception;

	/* 문의목록불러오기 */
	public List<ItemQnaDto> getReadItemQnaData(int itemQnaNum) throws Exception;

	/* 문의수정 */
	public void updateItemQna(ItemQnaDto qdto) throws Exception;
	
	/*문의삭제*/
	public void deleteItemQna(int itemQnaNum) throws Exception;
}
