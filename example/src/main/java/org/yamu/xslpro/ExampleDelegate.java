package org.yamu.xslpro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_core.net.RestClient;
import org.yamu.tea_core.net.callback.IError;
import org.yamu.tea_core.net.callback.IFailure;
import org.yamu.tea_core.net.callback.ISuccess;
import org.yamu.tea_core.ui.loader.LoaderStyle;

/**
 * Created by 马杰涛 on 2018/9/30
 */

public class ExampleDelegate extends TeaDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext(), LoaderStyle.CubeTransitionIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("haaaaaaaaaaaa", response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
