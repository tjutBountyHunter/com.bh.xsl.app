package org.yamu.xslpro;

import android.app.Application;

import com.facebook.stetho.Stetho;

import org.yamu.tea_core.app.Tea;
import org.yamu.tea_core.net.interceptor.DebugInterceptor;
import org.yamu.tea_sc.database.DatabaseManager;
import org.yamu.tea_sc.net.NetKey;

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

        initStetho();
        DatabaseManager.getInstance().init(this);

    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }
}
