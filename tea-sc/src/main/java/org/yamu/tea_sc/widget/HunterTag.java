package org.yamu.tea_sc.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable;


public class HunterTag extends QMUIRoundButton {

    public HunterTag(Context context) {
        super(context);
    }

    public HunterTag(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HunterTag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setText(String text) {
        this.setText(text);
    }

    public String getText() {
        return this.getText();
    }


    public void setBackgroundColor() {
        QMUIRoundButtonDrawable drawable = (QMUIRoundButtonDrawable) this.getBackground();
    }
}
