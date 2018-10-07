package org.yamu.tea_sc.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;

import butterknife.BindView;

/**
 * Created by mjt89 on 2018/10/6 0006
 */

public class NoticeDelegate extends TeaDelegate {

//    @BindView(R2.id.tab_notice)
//    QMUITabSegment tabSegment = null;
//    @BindView(R2.id.image_mess)

    private static NoticeDelegate INSTANCE = null;


    public static NoticeDelegate newInstance() {
        if (INSTANCE == null) {
            Bundle args = new Bundle();
            INSTANCE = new NoticeDelegate();
            INSTANCE.setArguments(args);
        }
        return INSTANCE;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_notice;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        tabSegment.setMode(QMUITabSegment.MODE_SCROLLABLE);
//        tabSegment.addTab(new QMUITabSegment.Tab("接的悬赏"));
//        tabSegment.addTab(new QMUITabSegment.Tab("接的悬赏"));
//        tabSegment.addTab(new QMUITabSegment.Tab("接的悬赏"));
//        tabSegment.addTab(new QMUITabSegment.Tab("接的悬赏"));
//        tabSegment.addTab(new QMUITabSegment.Tab("接的悬赏"));
//        tabSegment.addTab(new QMUITabSegment.Tab("接的悬赏"));
    }
}
