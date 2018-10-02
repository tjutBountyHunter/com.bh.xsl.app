package org.yamu.tea_sc.sign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_core.util.timer.BaseTimerTask;
import org.yamu.tea_core.util.timer.ITimerListener;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mjt89 on 2018/10/2 0002
 */
public class SignUpDelegate extends TeaDelegate implements TextWatcher, ITimerListener {

    @BindView(R2.id.topbar_sign_up)
    QMUITopBar topBar = null;
    @BindView(R2.id.et_phone)
    EditText mPhone = null;
    @BindView(R2.id.tv_ver_code)
    TextView mActionCode = null;
    @BindView(R2.id.et_ver_code)
    EditText mCode = null;
    @BindView(R2.id.et_password)
    EditText mPassword = null;
    @BindView(R2.id.et_re_password)
    EditText mRePassword = null;
    @BindView(R2.id.bt_action_next)
    QMUIRoundButton mActionNext = null;
    @BindView(R2.id.tv_signup_link)
    TextView mAgreementLink = null;

    private Timer mTimer = null;
    private int mCount = 60;

    @SuppressLint("ResourceAsColor")
    @OnClick(R2.id.tv_ver_code)
    void onClickSignUp() {
        mActionCode.setEnabled(false);
        mActionCode.setTextColor(getResources().getColor(R.color.qmui_config_color_gray_7));
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    public static SignUpDelegate newInstance(boolean isPopbackEnable) {
        Bundle args = new Bundle();
        args.putBoolean("isPopbackEnable", isPopbackEnable);
        SignUpDelegate fragment = new SignUpDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    private boolean checkForm() {
        final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String rePassword = mRePassword.getText().toString().trim();
        final String code = mCode.getText().toString().trim();

        boolean isPass = true;

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("请输入手机号");
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() > 16 || password.length() < 6) {
            mPassword.setError("请正确填写密码");
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.equals(password)) {
            mRePassword.setError("密码不一致");
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        setTextChangedListener();
        if (getPreFragment() instanceof SignInDelegate) {
            topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop();
                }
            });
        } else {
            topBar.setTitleGravity(Gravity.CENTER);
        }
        topBar.setTitle("注册");
    }

    private void setTextChangedListener() {
        mPhone.addTextChangedListener(this);
        mCode.addTextChangedListener(this);
        mPassword.addTextChangedListener(this);
        mRePassword.addTextChangedListener(this);
    }

    private void checkIsNextEnable() {
        final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String rePassword = mRePassword.getText().toString().trim();
        final String code = mCode.getText().toString().trim();

        boolean isPass = false;

        if (!phone.isEmpty() && !code.isEmpty() && !password.isEmpty() && !rePassword.isEmpty()) {
            isPass = true;
        }
        mActionNext.setEnabled(isPass);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkIsNextEnable();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mActionCode != null) {
                    mActionCode.setText(MessageFormat.format("重新获取{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            mActionCode.setEnabled(true);
                            mActionCode.setText("重新获取");
                            mActionCode.setTextColor(getResources().getColor(R.color.tea_app_theme_color));
                            mCount = 60;
                        }
                    }
                }
            }
        });
    }
}
