package org.yamu.tea_sc.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import org.yamu.tea_sc.R;


public class StatsTextView extends LinearLayout {
    private QMUIRadiusImageView imageView;
    private TextView textView;

    public StatsTextView(Context context) {
        super(context);
    }

    public StatsTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.wiget_stats_text, this);
        imageView = findViewById(R.id.image_stats_text);
        textView = findViewById(R.id.tv_stats_text);

    }

    public StatsTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setStats(String text, int color) {
        imageView.setImageResource(color);
        textView.setText(text);
        textView.setTextColor(color);
    }


}
