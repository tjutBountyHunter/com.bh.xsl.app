package org.yamu.tea_sc.hunterMaket.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by mjt89 on 2018/10/5 0005..
 */

public class MultipleItem implements MultiItemEntity {
    public static final int FENLEI = 1;
    public static final int MY_HUNTER = 2;
    public static final int HOT_HUNTER = 3;
    private int itemType;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}

