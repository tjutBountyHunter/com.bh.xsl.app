package org.yamu.xslpro;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIDeviceHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import org.yamu.tea_core.activities.ProxyActivity;
import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.home.HomeDelegate;
import org.yamu.tea_sc.launcher.LauncherDelegate;
import org.yamu.tea_sc.launcher.LauncherScrollDelegate;
import org.yamu.tea_sc.sign.AuthDelegate;
import org.yamu.tea_sc.sign.SignInDelegate;
import org.yamu.tea_sc.sign.SignUpDelegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class ExampleActivity extends ProxyActivity {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;  //点击返回键时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        QMUIStatusBarHelper.translucent(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(getResources().getColor(R.color.tea_app_theme_color));
//        }
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public TeaDelegate setRootDelegate() {
        return HomeDelegate.newInstance();
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
