package org.yamu.tea_sc.sign.authentication;

/**
 * Created by mjt89 on 2018/10/3 0003
 */

public interface IAuthNetCallback<T> {
    void onSuccess(T data, AuthItemTag tag);

    void onFaile();
}
