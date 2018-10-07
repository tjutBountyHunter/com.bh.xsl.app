package org.yamu.tea_sc.hunterMaket.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.yamu.tea_sc.R;
import org.yamu.tea_sc.hunterMaket.Entity.HunterMaketCard;
import org.yamu.tea_sc.taskHall.TaskEntity;
import org.yamu.tea_sc.widget.YMULevelText;

import java.util.List;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public class MyHunterRecyclerAdapter extends BaseQuickAdapter<HunterMaketCard, BaseViewHolder> {


    public MyHunterRecyclerAdapter(int layoutResId, @Nullable List<HunterMaketCard> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HunterMaketCard item) {
        switch (item.getId() % 3) {
            case 0:
                helper.setImageResource(R.id.imageView3, R.mipmap.touxiang_01)
                        .setText(R.id.tv_my_hunter_name, "小米渣")
                        .setText(R.id.tv_my_hunter_time, "上次雇佣：2018-9-14");
                break;
            case 1:
                helper.setImageResource(R.id.imageView3, R.mipmap.touxiang_02)
                        .setText(R.id.tv_my_hunter_name, "大洋芋")
                        .setText(R.id.tv_my_hunter_time, "上次雇佣：2018-9-7");
                break;
            case 2:
                helper.setImageResource(R.id.imageView3, R.mipmap.touxiang_03)
                        .setText(R.id.tv_my_hunter_name, "烧包谷")
                        .setText(R.id.tv_my_hunter_time, "上次雇佣：2018-9-5");
                break;

        }
    }

}
