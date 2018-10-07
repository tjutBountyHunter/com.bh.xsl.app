package org.yamu.tea_sc.personalCenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import org.yamu.tea_sc.R2;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.widget.YMULevelProgresBar;

import butterknife.BindView;


public class PersonalCenterDelegate extends TeaDelegate {
    @BindView(R2.id.topbar_personal_center)
    QMUITopBar mTopBar;
    @BindView(R2.id.collapsing_topbar_layout_personal_center)
    QMUICollapsingTopBarLayout qmuiCollapsingTopBarLayout;


    @BindView(R2.id.tabSegment_personal_center)
    QMUITabSegment mTabSegment;
    PersonalCenterPagerAdapter personalCenterPagerAdapter;
    @BindView(R2.id.personal_center_contentViewPager)
    ViewPager viewPager;

    PersonalCenterPagerAdapter.ContentPage mDestPage = PersonalCenterPagerAdapter.ContentPage.Item1;

    @BindView(R2.id.levelbar_boss)
    YMULevelProgresBar mBossProgressBar;
    @BindView(R2.id.levelbar_hunter)
    YMULevelProgresBar mHunterProgressBar;

    @BindView(R2.id.statusBarView)
    View statusBar = null;


    private void initView() {
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = QMUIStatusBarHelper.getStatusbarHeight(getContext());
        mTabSegment.addOnTabSelectedListener(tabSelectedListener);
        mBossProgressBar.setMaxValue(100);
        mBossProgressBar.setTextColor(getResources().getColor(R.color.app_color_white));
        mBossProgressBar.setProgress(67);
        mBossProgressBar.setQMUIProgressBarTextGenerator(new YMULevelProgresBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(YMULevelProgresBar progressBar, int value, int maxValue) {
                return value + "/" + maxValue;
            }
        });

        mHunterProgressBar.setMaxValue(100);
        mHunterProgressBar.setTextColor(getResources().getColor(R.color.app_color_white));
        mHunterProgressBar.setProgress(78);
        mHunterProgressBar.setQMUIProgressBarTextGenerator(new YMULevelProgresBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(YMULevelProgresBar progressBar, int value, int maxValue) {
                return value + "/" + maxValue;
            }
        });

    }


    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        qmuiCollapsingTopBarLayout.setTitle("昵称");
    }

    private void initTabAndPager() {

    }

    public void setCurrentItem(int pageItem, int item) {
        viewPager.setCurrentItem(pageItem);
        PersonalCenterOfferRewardDelegate.getInstance().setTopBarcheckedItem(item);
    }

    private QMUITabSegment.OnTabSelectedListener tabSelectedListener = new QMUITabSegment.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int index) {
        }

        @Override
        public void onTabUnselected(int index) {
        }

        @Override
        public void onTabReselected(int index) {

        }

        @Override
        public void onDoubleTap(int index) {

        }
    };

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_center;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        personalCenterPagerAdapter = new PersonalCenterPagerAdapter(getFragmentManager());
        viewPager.setAdapter(personalCenterPagerAdapter);
        viewPager.setCurrentItem(0, false);
        mTabSegment.addTab(new QMUITabSegment.Tab("主页"));
        mTabSegment.addTab(new QMUITabSegment.Tab("悬赏"));
        mTabSegment.addTab(new QMUITabSegment.Tab("收藏"));
        mTabSegment.setHasIndicator(true);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.setupWithViewPager(viewPager, false);

        initView();
        initTopBar();
        initTabAndPager();
    }
}
