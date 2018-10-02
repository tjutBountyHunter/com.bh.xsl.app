package org.yamu.tea_core.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by 马杰涛 on 2018/9/30
 */

public class Tea {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getTeaConfigs();
    }

    public static <T> T getConfiguration(ConfigKeys configKeys) {
        return (T) Configurator.getInstance().getConfiguration(configKeys);
    }

    public static Context getAppliactionContext() {
        return Configurator.getInstance().getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

}
