package org.yamu.tea_sc.taskHall;

import org.yamu.tea_core.mvp.ITeaView;

import java.util.List;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

interface TaskHallView<T> extends ITeaView<T> {

    void showAcceptTask();

    void showSuccess(T data, TaskHallModel.FlagType type);

}
