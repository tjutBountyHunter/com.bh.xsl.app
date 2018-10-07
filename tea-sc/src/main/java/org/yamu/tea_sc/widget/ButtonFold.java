package org.yamu.tea_sc.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import org.yamu.tea_sc.R;


public class ButtonFold extends ConstraintLayout {
    private TextView title;
    private ImageView icon;

    public ButtonFold(Context context) {
        super(context);
        setup(context, null);
    }

    public ButtonFold(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public ButtonFold(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_fold_button, this, true);
        title = findViewById(R.id.tv_title);
        icon = findViewById(R.id.image_icon);
    }


    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }


}
