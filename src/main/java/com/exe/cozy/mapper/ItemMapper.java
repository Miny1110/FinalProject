package com.exe.cozy.mapper;

import com.exe.cozy.domain.ItemDetailDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
    public int itemMaxNum() throws Exception;
    public void insertItem(ItemDetailDto idto) throws Exception;
    public int getItemDataCount(String searchKey,String searchValue) throws Exception;
    public void deleteItemData(int itemNum) throws Exception;
    public void updateItemHitCount(int itemNum) throws Exception;
    public ItemDetailDto getReadItemData(int itemNum) throws Exception;


}
