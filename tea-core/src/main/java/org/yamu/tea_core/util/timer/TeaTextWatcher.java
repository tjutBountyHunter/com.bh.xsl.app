package org.yamu.tea_core.util.timer;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by mjt89 on 2018/10/2 0002
 */
public abstract class TeaTextWatcher implements TextWatcher {
    private ITimerListener listener = null;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    interface ITextChangedListener {
        void onTextChanged(CharSequence s, int start, int before, int count);
    }
}
