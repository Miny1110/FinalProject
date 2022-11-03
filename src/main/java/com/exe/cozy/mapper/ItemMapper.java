package com.exe.cozy.mapper;

import com.exe.cozy.domain.ItemDetailDto;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
    public int itemMaxNum() throws Exception;
    public int fileMaxNum() throws Exception;
    public void insertItem(ItemDetailDto idto) throws Exception;
    public void insertItemMap(Map<String, Object> idto) throws Exception;
    public int getItemDataCount(String searchKey,String searchValue) throws Exception;
    public void deleteItemData(int itemNum) throws Exception;
    public void updateItemHitCount(int itemNum) throws Exception;
    public void updateItemStock(int itemNum,int itemStock) throws Exception;
    public ItemDetailDto getReadItemData(int itemNum) throws Exception;
    public void insertFile(Map<String, Object> map);

	
}
