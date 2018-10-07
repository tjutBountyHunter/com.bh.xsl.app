package org.yamu.tea_sc.release;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.qmuiteam.qmui.widget.QMUITopBar;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mjt89 on 2018/10/6 0006
 */

public class ReleaseTaskDelegate extends TeaDelegate {

    @BindView(R2.id.tv_release_deadline)
    TextView mDedalineView = null;

    @OnClick(R2.id.tv_release_deadline)
    void onClickDeadline() {
        showTimePickerDialog(getContext(), mDedalineView, Calendar.getInstance());
    }

    @BindView(R2.id.topbar_release)
    QMUITopBar mTopBar = null;

    private static ReleaseTaskDelegate INSTANCE;

    public static ReleaseTaskDelegate newInstance() {
        if (INSTANCE == null) {
            Bundle args = new Bundle();

            INSTANCE = new ReleaseTaskDelegate();
            INSTANCE.setArguments(args);
        }
        return INSTANCE;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_release_task;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mTopBar.setTitle("发布悬赏");
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });


    }

    public static void showTimePickerDialog(Context ctx, final TextView tv, Calendar calendar) {
        new TimePickerDialog(ctx,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view,
                                          int hourOfDay, int minute) {
                        tv.setText("您选择了：" + hourOfDay + "时" + minute
                                + "分");
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                , true).show();
    }

}
