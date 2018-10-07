package org.yamu.tea_sc.taskHall;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.yamu.tea_core.mvp.ITeaView;
import org.yamu.tea_core.mvp.TeaModel;
import org.yamu.tea_core.mvp.TeaPresenter;
import org.yamu.tea_sc.taskHall.callback.TaskModelCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.cache.InternalCache;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public class TaskHallPresenter extends TeaPresenter<String, TaskHallModel, TaskHallView> implements TaskModelCallback<String> {

    private List<TaskEntity> mTasks = new ArrayList<>();

    public final void initTaskItem() {
        this.mModel.initData();
    }

    @Override
    public void onFailure() {
        view.showFailure();
    }

    @Override
    public void onError(String msg) {
        view.showError(msg);
    }

    @Override
    public void onComplet() {
        view.hideLoading();
    }

    @Override
    public void onStart() {
        view.showLoading();
    }

    @Override
    protected TaskHallModel getModel() {
        return new TaskHallModel(this);
    }

    public void acceptTask(int position) {
        mModel.acceptTask(mTasks.get(position).getTaskId());
    }

    @Override
    public void onAcceptTaskSuccess() {
        view.showAcceptTask();
    }

    @Override
    public void onTaskSuccess(String data, Object flag) {
        JSONObject response = JSON.parseObject(data);
        JSONArray datas = response.getJSONObject("data").getJSONArray("findPage");
        JSONObject jsonObject;
        TaskEntity task;
        final int size = datas.size();
        mTasks.clear();
        for (int i = 0; i < size; i++) {
            jsonObject = datas.getJSONObject(i);
            task = jsonObject.toJavaObject(TaskEntity.class);
            mTasks.add(task);
            Log.d("------", task.toString());
        }

        if (!mTasks.isEmpty()) {
            if (flag != TaskHallModel.FlagType.LOAD_MORE) {
                TaskHallModel.NEW_TASK_ID = mTasks.get(0).getId();
                TaskHallModel.OLD_TASK_ID = mTasks.get(0).getId();
            } else {
                TaskHallModel.OLD_TASK_ID = mTasks.get(size - 1).getId();
            }
            TaskHallModel.TASK_ENTITY_LIST.addAll(mTasks);
            TaskHallModel.ITEM_COUNT = TaskHallModel.TASK_ENTITY_LIST.size();
        }
        view.showSuccess(mTasks, (TaskHallModel.FlagType) flag);
    }

    public void updateTaskItem() {
        mModel.updata();
    }

    public void loadMore(int size) {
        mModel.loadMore(size);
    }

    @Override
    public void onSuccess(String data) {

    }
}
