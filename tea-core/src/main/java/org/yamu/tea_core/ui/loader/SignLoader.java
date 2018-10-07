package org.yamu.tea_core.ui.loader;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.wang.avi.AVLoadingIndicatorView;

import org.yamu.tea_core.R;
import org.yamu.tea_core.app.Tea;
import org.yamu.tea_core.util.dimen.DimenUtil;

import java.util.ArrayList;

/**
 * Created by mjt89 on 2018/10/3 0003
 */

public class SignLoader {
    private static String message;
    private static final Context CONTEXT = Tea.getAppliactionContext();
    private static final LinearLayout.LayoutParams LP_LOADER =
            new LinearLayout.LayoutParams(QMUIDisplayHelper.dp2px(CONTEXT, 32), QMUIDisplayHelper.dp2px(CONTEXT, 32));

    private static final LinearLayout.LayoutParams LP_TEXT =
            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    private static final int LOAD_HEIGHT = QMUIDisplayHelper.dp2px(CONTEXT, 80);
    private static final int LOAD_HORIZONTAL_MARGIN = QMUIDisplayHelper.dp2px(CONTEXT, 32);

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String DEFAUT_LOADER = LoaderStyle.PacmanIndicator.name();

    public SignLoader(String message) {
        this.message = message;
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog_text);

        final LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackground(CONTEXT.getResources().getDrawable(R.drawable.sign_loading_dialog_bg));
        } else {
            layout.setBackgroundColor(CONTEXT.getResources().getColor(R.color.qmui_config_color_white));
        }

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreater.create(type, context);
        avLoadingIndicatorView.setIndicatorColor(context.getResources().getColor(R.color.tea_app_theme_color));
        LP_LOADER.setMargins(LOAD_HORIZONTAL_MARGIN, 0, 0, 0);

        final TextView tip = new TextView(context);
        tip.setText("正在登录...");
        tip.setTextSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            LP_TEXT.setMarginStart(QMUIDisplayHelper.dp2px(CONTEXT, 16));
        } else {
            LP_TEXT.setMargins(QMUIDisplayHelper.dp2px(CONTEXT, 16), 0, 0, 0);
        }

        layout.addView(avLoadingIndicatorView, LP_LOADER);
        layout.addView(tip, LP_TEXT);
        dialog.setContentView(layout);
        dialog.setCancelable(false);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviHeight = DimenUtil.getScreenheight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth - LOAD_HORIZONTAL_MARGIN * 2;
            lp.height = LOAD_HEIGHT;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAUT_LOADER);
    }


    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null)
                if (dialog.isShowing())
                    dialog.cancel();
        }
    }
}
