package org.yamu.tea_sc.hunterMaket.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.yamu.tea_sc.R;
import org.yamu.tea_sc.hunterMaket.Entity.HunterMaketClass;
import org.yamu.tea_sc.taskHall.TaskEntity;

import java.util.List;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public class TagHunterRecyclerAdapter extends BaseQuickAdapter<HunterMaketClass, BaseViewHolder> {


    public TagHunterRecyclerAdapter(int layoutResId, @Nullable List<HunterMaketClass> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HunterMaketClass item) {
        helper.setImageResource(R.id.image_icon, item.getImageResId())
                .setText(R.id.tv_title, item.getTitle());
    }

}
