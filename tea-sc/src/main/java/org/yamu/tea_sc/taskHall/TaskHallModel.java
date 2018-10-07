package org.yamu.tea_sc.taskHall;

import android.util.Log;

import org.yamu.tea_core.app.ConfigKeys;
import org.yamu.tea_core.mvp.IModelCallback;
import org.yamu.tea_core.mvp.TeaModel;
import org.yamu.tea_core.net.RestClient;
import org.yamu.tea_core.net.callback.IError;
import org.yamu.tea_core.net.callback.IFailure;
import org.yamu.tea_core.net.callback.IRequest;
import org.yamu.tea_core.net.callback.ISuccess;
import org.yamu.tea_core.util.storage.TeaPreference;
import org.yamu.tea_sc.database.DatabaseManager;
import org.yamu.tea_sc.net.NetKey;
import org.yamu.tea_sc.taskHall.callback.TaskModelCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

class TaskHallModel extends TeaModel<TaskModelCallback> {

    private static int CURRENT_TASK_POSITION = 0;
    public static int ITEM_COUNT;
    public static final List<TaskEntity> TASK_ENTITY_LIST = new ArrayList<>();
    public static String NEW_TASK_ID = "0";
    public static String OLD_TASK_ID = "0";

    enum FlagType {
        INIT,
        LOAD_MORE,
        REFARSH
    }


    public TaskHallModel(TaskModelCallback callback) {
        super(callback);
    }

    public void getItemData(String newTaskId, final FlagType type, int size) {
//        Log.d("任务大厅任务列表初始化请求调试", "start url: " + NetKey.TASK_HALL + "?flagid=" + newTaskId + "&type=" + type.ordinal() + "  " + size);
        Log.e("Tag", "-------" + NEW_TASK_ID);
        RestClient.builder()
                .url(NetKey.TASK_HALL)
                .params("flagid", newTaskId)
                .params("type", type.ordinal())
                .params("rows", size)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("任务大厅任务列表初始化请求调试--成功", response.toString());
                        callback.onTaskSuccess(response, type);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        callback.onFailure();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        callback.onError(msg);
                    }
                })
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        Log.e("Request", "start----");
                        callback.onStart();
                    }

                    @Override
                    public void onRequestEnd() {
                        Log.e("Request", "end----");
                        callback.onComplet();
                    }
                })
                .build()
                .get();
    }

    public void initData() {
        getItemData(NEW_TASK_ID, FlagType.INIT, 10);
    }

    public void updata() {
        getItemData(NEW_TASK_ID, FlagType.REFARSH, 10);
    }

    public void acceptTask(String taskId) {
        String userId = TeaPreference.getAppInfimation(ConfigKeys.USERID.name());
        RestClient.builder()
                .url(NetKey.ACCEPT_TASK)
                .params("taskId", taskId)
                .params("hunterId", userId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("接受任务调试", response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        callback.onFailure();

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        callback.onError(msg);
                        Log.d("接受任务调试-错误", msg);
                    }
                })
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        callback.onStart();
                    }

                    @Override
                    public void onRequestEnd() {
                        callback.onComplet();
                    }
                })
                .build()
                .get();
    }

    public void loadMore(int size) {
        getItemData(OLD_TASK_ID, FlagType.LOAD_MORE, size);
    }
}
