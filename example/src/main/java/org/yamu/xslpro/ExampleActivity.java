package org.yamu.xslpro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import org.yamu.tea_core.activities.ProxyActivity;
import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.launcher.LauncherDelegate;
import org.yamu.tea_sc.launcher.LauncherScrollDelegate;
import org.yamu.tea_sc.sign.SignInDelegate;
import org.yamu.tea_sc.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public TeaDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
