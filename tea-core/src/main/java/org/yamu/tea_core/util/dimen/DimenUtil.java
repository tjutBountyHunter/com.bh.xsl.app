package org.yamu.tea_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.yamu.tea_core.app.Tea;

/**
 * Created by 马杰涛 on 2018/10/1
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Tea.getAppliactionContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenheight() {
        final Resources resources = Tea.getAppliactionContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
