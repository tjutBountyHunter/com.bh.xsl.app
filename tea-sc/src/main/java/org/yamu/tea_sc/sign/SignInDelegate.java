package org.yamu.tea_sc.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.yamu.tea_core.delegate.BaseDelegate;
import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_core.net.RestClient;
import org.yamu.tea_core.net.callback.IFailure;
import org.yamu.tea_core.net.callback.ISuccess;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;
import org.yamu.tea_sc.net.NetKey;

import java.util.zip.Adler32;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mjt89 on 2018/10/2 0002
 */
public class SignInDelegate extends TeaDelegate implements TextWatcher {

    @BindView(R2.id.bt_action_sign_up_link)
    QMUIRoundButton mSignUpLink = null;
    @BindView(R2.id.bt_action_sign_in)
    QMUIRoundButton mActionSignIn = null;

    @BindView(R2.id.et_sign_in_phone)
    EditText mPhone = null;

    @BindView(R2.id.et_sign_in_password)
    EditText mPassword = null;


    @OnClick(R2.id.bt_action_sign_up_link)
    void clickSignUp() {
        start(new SignUpDelegate());
    }

    @OnClick(R2.id.bt_action_sign_in)
    void onClickSignIn() {
        Log.d("请求地址",NetKey.LOGIN);
        RestClient.builder()
                .url(NetKey.LOGIN)
                .loader(getContext())
                .params("username", mPhone.getText().toString().trim())
                .params("password", mPassword.getText().toString().trim())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("登录成功", response.toString());
                        Signhandler.onSignIn("sss");
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d("登录失败","---");
                    }
                })
                .build()
                .post();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mPhone.addTextChangedListener(this);
        mPassword.addTextChangedListener(this);
    }

    private void checkSignInEnable() {
        if (mPhone.getText().toString().trim().isEmpty() || mPassword.getText().toString().trim().isEmpty())
            mActionSignIn.setEnabled(false);
        else
            mActionSignIn.setEnabled(true);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkSignInEnable();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
