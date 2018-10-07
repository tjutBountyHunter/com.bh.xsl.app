package org.yamu.tea_sc.hunterMaket.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.yamu.tea_sc.R;

import java.util.List;

/**
 * Created by mjt89 on 2018/10/5 0005..
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(List data) {
        super(data);
        addItemType(MultipleItem.FENLEI, R.layout.item_hunter_maket_class);
        addItemType(MultipleItem.MY_HUNTER, R.layout.item_hunter_my);
        addItemType(MultipleItem.HOT_HUNTER, R.layout.item_hot_hunter_card);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.FENLEI:

                break;
            case MultipleItem.MY_HUNTER:

                break;
            case MultipleItem.HOT_HUNTER:

                break;
            default:
                break;
        }
    }

}
