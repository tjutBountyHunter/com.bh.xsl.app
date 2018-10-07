package org.yamu.tea_sc.personalCenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class OfferRewardRecyclerAdapter extends BaseQuickAdapter<OfferReward, BaseViewHolder> {
    public OfferRewardRecyclerAdapter(int layoutResId, @Nullable List<OfferReward> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OfferReward item) {

    }

}



