package org.yamu.tea_sc.sign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    void onClickgetCode() {
        if (checkPhoneFormat()) {
            mActionCode.setEnabled(false);
            initTimer();
        }
    }

    @OnClick(R2.id.bt_action_next)
    void onClickNext() {
//        if (checkForm()) {
//            // 提交表单
//        }
        start(AuthDelegate.newInstance());
    }

    private boolean checkPhoneFormat() {
        if (mPhone != null) {
            if (mPhone.getText().toString().trim().length() != 11) {
                Toast.makeText(getContext(), "手机格式不正确", Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }
        return false;
    }

    private static SignUpDelegate INSTANCE;

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    public static SignUpDelegate newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SignUpDelegate();
            Bundle args = new Bundle();
            INSTANCE.setArguments(args);
        }
        return INSTANCE;
    }

    private boolean checkForm() {
        String message = "";
        final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String rePassword = mRePassword.getText().toString().trim();
        final String code = mCode.getText().toString().trim();

        boolean isPass = false;

        if (phone.isEmpty() || phone.length() != 11) {
            message = "手机格式不正确";
        } else if (password.isEmpty() || password.length() > 16 || password.length() < 6) {
            message = "密码格式不正确";
        } else if (rePassword.isEmpty() || !rePassword.equals(password)) {
            message = "密码输入不一致";
        }
        if ("".equals(message))
            isPass = true;
        else
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
        checkgetCodeEnable();
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
                            mCount = 60;
                            mActionCode.setText("重新获取");
                        }
                    }
                }
                checkgetCodeEnable();
            }
        });
    }

    private void checkgetCodeEnable() {
        if (mActionCode != null) {
            if (mCount != 60) {
                mActionCode.setEnabled(false);
                mActionCode.setTextColor(getResources().getColor(R.color.qmui_config_color_gray_7));
            } else {
                mActionCode.setEnabled(true);
                mActionCode.setTextColor(getResources().getColor(R.color.tea_app_theme_color));
            }
        }
    }
}
