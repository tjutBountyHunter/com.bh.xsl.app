package org.yamu.tea_core.mvp;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public interface IModelCallback<T> {
    void onSuccess(T data);

    void onFailure();

    void onError(String msg);

    void onComplet();

    void onStart();

}
