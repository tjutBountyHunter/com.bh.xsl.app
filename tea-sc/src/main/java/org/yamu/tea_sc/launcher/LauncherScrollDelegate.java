package org.yamu.tea_sc.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_core.ui.launcher.LauncherHolderCreator;
import org.yamu.tea_core.ui.launcher.ScrollLauncherTag;
import org.yamu.tea_core.util.storage.TeaPreference;
import org.yamu.tea_core.util.ui.CommonUtil;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.sign.SignInDelegate;

import java.util.ArrayList;

/**
 * Created by mjt89 on 2018/10/2 0002
 */
public class LauncherScrollDelegate extends TeaDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        // 如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            Log.d("滑动启动页最后一页点击", "设置第一次启动flag为true");
            TeaPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            CommonUtil.cancelFullScreen(getActivity());
            startWithPop(new SignInDelegate());
        }
    }
}
