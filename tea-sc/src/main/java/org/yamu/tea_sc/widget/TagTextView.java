package org.yamu.tea_sc.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import org.yamu.tea_sc.R;


public class TagTextView extends android.support.v7.widget.AppCompatTextView {


    public TagTextView(Context context) {
        super(context);
        setup(context, null);
    }

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        setTagTextSize(12);
        setTagBackground(context);
        this.setPadding(8, 4, 8, 4);
    }

    private void setTagBackground(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setBackground(context.getDrawable(R.drawable.tv_task_tag));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(getResources().getDrawable(R.drawable.tv_task_tag));
        } else {
            this.setBackgroundColor(getResources().getColor(R.color.qmui_config_color_gray_9));
        }
    }

    public void setTagTextSize(int tagTextSize) {
        this.setTextSize(tagTextSize);
    }
}
