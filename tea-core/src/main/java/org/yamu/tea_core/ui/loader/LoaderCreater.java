package org.yamu.tea_core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by 马杰涛 on 2018/10/1
 */

public final class LoaderCreater {

    private static final WeakHashMap<String, Indicator> LOADING_WAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_WAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_WAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_WAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaulePackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaulePackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);

        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
