package com.exe.cozy.itemDetail;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemDetailService")
public class ItemDetailServiceImpl implements ItemDetailService {
@Autowired
private ItemMapper itemMapper;

    @Override
    public int itemMaxNum() throws Exception {
        return itemMapper.itemMaxNum();
    }

    @Override
    public void insertItem(ItemDetailDto idto) throws Exception {
        itemMapper.insertItem(idto);
    }

    @Override
    public int getItemDataCount(String searchKey, String searchValue) throws Exception {
        return itemMapper.getItemDataCount(searchKey,searchValue);
    }

    @Override
    public void deleteItemData(int itemNum) throws Exception {
        itemMapper.deleteItemData(itemNum);

    }

    @Override
    public void updateItemHitCount(int itemNum) throws Exception {
        itemMapper.updateItemHitCount(itemNum);
    }

    @Override
    public ItemDetailDto getReadItemData(int itemNum) throws Exception {
        return itemMapper.getReadItemData(itemNum);
    }
}
