package org.yamu.tea_core.net.callback;

import android.os.Handler;

import org.yamu.tea_core.ui.loader.LoaderStyle;
import org.yamu.tea_core.ui.loader.SignLoader;
import org.yamu.tea_core.ui.loader.TeaLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 马杰涛 on 2018/10/1
 */

public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest iRequest, ISuccess iSuccess, IFailure iFailure, IError iError, LoaderStyle style) {
        this.REQUEST = iRequest;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted())
                if (SUCCESS != null)
                    SUCCESS.onSuccess(response.body());
        } else {
            if (ERROR != null)
                ERROR.onError(response.code(), response.message());
        }
        if (REQUEST != null)
            REQUEST.onRequestEnd();
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null)
            FAILURE.onFailure();
        if (REQUEST != null)
            REQUEST.onRequestEnd();
        stopLoading();
    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SignLoader.stopLoading();
                }
            }, 1000);
        }
    }

}
