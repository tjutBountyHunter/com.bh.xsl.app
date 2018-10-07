package org.yamu.tea_sc.widget;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import org.yamu.tea_sc.R;


public class XslClickSpan extends ClickableSpan {
    private Context context;


    public XslClickSpan(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View widget) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(context.getResources().getColor(R.color.tea_app_theme_color));
        ds.setUnderlineText(false);
    }
}
