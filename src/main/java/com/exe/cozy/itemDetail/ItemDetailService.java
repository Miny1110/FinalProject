package com.exe.cozy.itemDetail;


import com.exe.cozy.domain.ItemDetailDto;
import org.springframework.web.method.support.ModelAndViewContainer;

public interface ItemDetailService {



    public int itemMaxNum() throws Exception;
    public void insertItem(ItemDetailDto idto) throws Exception;
    public int getItemDataCount(String searchKey,String searchValue) throws Exception;
    public void deleteItemData(int itemNum) throws Exception;
    public void updateItemHitCount(int itemNum) throws Exception;
    public ItemDetailDto getReadItemData(int itemNum) throws Exception;

}
