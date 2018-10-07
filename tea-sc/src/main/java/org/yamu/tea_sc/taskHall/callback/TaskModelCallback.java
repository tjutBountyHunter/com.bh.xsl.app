package org.yamu.tea_sc.taskHall.callback;

import org.yamu.tea_core.mvp.IModelCallback;
import org.yamu.tea_sc.taskHall.TaskEntity;

import java.util.List;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public interface TaskModelCallback<T> extends IModelCallback<T> {
    void onAcceptTaskSuccess();

    void onTaskSuccess(T data, Object flag);
}
