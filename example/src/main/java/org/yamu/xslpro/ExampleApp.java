package org.yamu.xslpro;

import android.app.Application;

import org.yamu.tea_core.app.Tea;
import org.yamu.tea_core.net.interceptor.DebugInterceptor;
import org.yamu.tea_sc.database.DatabaseManager;
import org.yamu.xslpro.net.NetKey;

/**
 * Created by 马杰涛 on 2018/9/30
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Tea.init(this)
                .withApiHost(NetKey.API_HOST)
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
    }
}
