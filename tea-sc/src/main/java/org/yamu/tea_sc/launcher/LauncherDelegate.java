package org.yamu.tea_sc.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import org.yamu.tea_core.app.Tea;
import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_core.net.callback.IError;
import org.yamu.tea_core.ui.launcher.ScrollLauncherTag;
import org.yamu.tea_core.util.storage.TeaPreference;
import org.yamu.tea_core.util.timer.BaseTimerTask;
import org.yamu.tea_core.util.timer.ITimerListener;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;
import org.yamu.tea_sc.sign.SignInDelegate;
import org.yamu.tea_sc.sign.SignUpDelegate;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 马杰涛 on 2018/10/2
 */

public class LauncherDelegate extends TeaDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    // 判断是否展示滑动启动页
    private void checkIsShowScroll() {
        TeaPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), false);
        Log.d("判断是否展示滑动启动页", "第一次启动app？ ： " + !TeaPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name()));
        if (!TeaPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            startWithPop(new LauncherScrollDelegate());
        } else {
            // 检查用户是否登录了APP
            startWithPop(new SignInDelegate());
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
