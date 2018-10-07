package org.yamu.tea_core.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.view.WindowManager;

import com.qmuiteam.qmui.util.QMUIColorHelper;
import com.qmuiteam.qmui.util.QMUIDeviceHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import org.yamu.tea_core.R;
import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_core.util.ui.CommonUtil;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by 马杰涛 on 2018/9/30
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract TeaDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle saveInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
//        CommonUtil.setFullScreen(this);
        setContentView(container);
        if (saveInstanceState == null)
            loadRootFragment(R.id.delegate_container, setRootDelegate());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
