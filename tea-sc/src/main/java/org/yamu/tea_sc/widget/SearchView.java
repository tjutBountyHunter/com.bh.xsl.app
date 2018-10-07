package org.yamu.tea_sc.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.yamu.tea_sc.R;


public class SearchView extends FrameLayout implements TextWatcher, View.OnClickListener {
    private EditText search;
    private ImageView cancel;

    public SearchView(@NonNull Context context) {
        super(context);
        setup(context, null);

    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }


    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);

    }

    private void setup(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_searchview, this);
        search = findViewById(R.id.ed_search_widget);
        cancel = findViewById(R.id.image_cancel);
        search.addTextChangedListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setCancelIconStatus(isEmptyInput());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public boolean isEmptyInput() {
        return search.getText().toString().trim().isEmpty();
    }

    public void setCancelIconStatus(boolean cancelIconStatus) {
        cancel.setVisibility(cancelIconStatus ? GONE : VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image_cancel) {
            search.setText("");
        }
    }
}
