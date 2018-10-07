package org.yamu.tea_sc.personalCenter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;
import org.yamu.tea_sc.widget.LineButton;

import butterknife.BindView;


public class PersonalCenterHomeDelegate extends TeaDelegate {
    private static PersonalCenterHomeDelegate fragment;

    @BindView(R2.id.linbt_offer_reward)
    LineButton offerRewardButton = null;

    @BindView(R2.id.linbt_reputation)
    LineButton reputationButton;
    @BindView(R2.id.bt_yifa)
    View viewButton1;
    @BindView(R2.id.bt_yijie)
    View viewButton2;
    @BindView(R2.id.bt_daiwanchen)
    View viewButton3;
    @BindView(R2.id.bt_wanchen)
    View viewButton4;


    private PersonalCenterDelegate activity;

    public static Fragment newInstance() {
        if (fragment == null)
            fragment = new PersonalCenterHomeDelegate();
        return fragment;

    }

    private void initView() {

        offerRewardButton.setTitleText("悬赏");
        offerRewardButton.setLeftText("查看更多");
        offerRewardButton.setTitleStyle(Typeface.DEFAULT);


        viewButton1.setOnClickListener(clickListener);
        viewButton2.setOnClickListener(clickListener);
        viewButton3.setOnClickListener(clickListener);
        viewButton4.setOnClickListener(clickListener);

        reputationButton.setTitleStyle(Typeface.DEFAULT);
        reputationButton.setTitleText("我的信誉");
        reputationButton.isRightText(false);
//        activity = (PersonalCenterDelegate) getActivity();
    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.bt_yifa:
//                    activity.setCurrentItem(1, 1);
//                    break;
//                case R.id.bt_yijie:
//                    activity.setCurrentItem(1, 2);
//                    break;
//                case R.id.bt_daiwanchen:
//                    activity.setCurrentItem(1, 3);
//                    break;
//                case R.id.bt_wanchen:
//                    activity.setCurrentItem(1, 4);
//                    break;
//                default:
//                    activity.setCurrentItem(1, 0);
//                    break;
//            }
        }
    };

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_center_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
    }
}
