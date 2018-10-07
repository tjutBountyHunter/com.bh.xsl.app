package org.yamu.tea_sc.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;
import org.yamu.tea_sc.notice.NoticeDelegate;
import org.yamu.tea_sc.pageAdapter.HomePagerAdapter;
import org.yamu.tea_sc.personalCenter.PersonalCenterDelegate;
import org.yamu.tea_sc.release.ReleaseTaskDelegate;

import java.nio.FloatBuffer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mjt89 on 2018/10/3 0003
 */

public class HomeDelegate extends TeaDelegate {

    @BindView(R2.id.tabSegment_home)
    QMUITabSegment tabSegment = null;
    @BindView(R2.id.vp_home)
    ViewPager viewPager = null;
    @BindView(R2.id.iv_touxiang)
    QMUIRadiusImageView mTouXiang = null;
    @BindView(R2.id.fab)
    FloatingActionButton mFButton = null;
    @BindView(R2.id.image_message)
    ImageView mMessage = null;

    @OnClick(R2.id.image_message)
    void onClickMessage() {
        start(NoticeDelegate.newInstance());
    }

    private HomePagerAdapter adapter = null;

    @OnClick(R2.id.iv_touxiang)
    void onClick() {
        start(new PersonalCenterDelegate());
    }

    @OnClick(R2.id.fab)
    void onClickFab() {
        start(ReleaseTaskDelegate.newInstance());
    }


    @SuppressLint("StaticFieldLeak")
    private static HomeDelegate mDelegate = null;

    public static HomeDelegate newInstance() {
        if (mDelegate == null) {
            mDelegate = new HomeDelegate();
        }
        return mDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        QMUIStatusBarHelper.translucent(getActivity());
        initTabView();
    }

    private final void initTabView() {
        QMUIStatusBarHelper.translucent(_mActivity);

        adapter = new HomePagerAdapter(getFragmentManager());

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0, false);
        tabSegment.addTab(new QMUITabSegment.Tab("任务大厅"));
        tabSegment.addTab(new QMUITabSegment.Tab("猎手市场"));
        tabSegment.setHasIndicator(true);
        tabSegment.setMode(QMUITabSegment.MODE_FIXED);
        tabSegment.setupWithViewPager(viewPager, false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
