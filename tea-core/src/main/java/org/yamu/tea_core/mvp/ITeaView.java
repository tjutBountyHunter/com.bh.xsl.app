package org.yamu.tea_core.mvp;

import java.util.List;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public interface ITeaView<T> {

    void showLoading();

    void hideLoading();

    void showSuccess(T data);

    void showError(String msg);

    void showFailure();
}
