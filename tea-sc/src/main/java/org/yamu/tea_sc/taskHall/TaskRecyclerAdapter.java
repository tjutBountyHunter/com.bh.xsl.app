package org.yamu.tea_sc.taskHall;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.yamu.tea_sc.R;
import org.yamu.tea_sc.widget.YMULevelText;

import java.util.List;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public class TaskRecyclerAdapter extends BaseQuickAdapter<TaskEntity, BaseViewHolder> {


    public TaskRecyclerAdapter(int layoutResId, @Nullable List<TaskEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskEntity item) {
        helper.setText(R.id.tv_task_user_name, item.getName())
                .setText(R.id.tv_task_time, item.getTime())
                .setText(R.id.tv_money, item.getMoney())
                .setText(R.id.tv_task_content, item.getContent())
                .setText(R.id.tv_task_deadline, "截止日期：" + item.getDeadLine())
                .addOnClickListener(R.id.bt_jiedan)
                .addOnClickListener(R.id.bt_siliao);
        ((YMULevelText) helper.getView(R.id.lt_master)).setmNumText(String.valueOf(item.getLevel()));
        Glide.with(mContext).load(item.getHeadImage()).into((ImageView) helper.getView(R.id.iv_head_portrait));
    }

}
