package org.yamu.tea_sc.taskHall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.yamu.tea_core.app.ConfigKeys;
import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_core.util.storage.TeaPreference;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public class TaskHallDelegate extends TeaDelegate implements TaskHallView<List<TaskEntity>> {

    private static int TOTAL_COUNTER = 0;
    @BindView(R2.id.refresh_task)
    SwipeRefreshLayout mRefresh = null;
    @BindView(R2.id.rev_task)
    RecyclerView mRecyclerView = null;

    private TaskRecyclerAdapter mTaskAdapter = new TaskRecyclerAdapter(R.layout.item_task_hall, null);
    private static TaskHallDelegate INSTANCE = null;
    private final TaskHallPresenter mPresenter = new TaskHallPresenter();
    private final int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private final List<TaskEntity> mDatas = new ArrayList<>();
    private int mCurrentCounter = 0;

    public static TeaDelegate newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskHallDelegate();
        }
        return INSTANCE;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_task_hall;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mPresenter.attchView(this);
        TeaPreference.setAppInfimation(ConfigKeys.USERID.name(), "1");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mTaskAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTaskAdapter);
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.tea_app_theme_color), getResources().getColor(R.color.xsl_green));
        if (mPresenter.isAttched())
            mPresenter.initTaskItem();
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mPresenter.isAttched())
                    mPresenter.updateTaskItem();
            }
        });

        mTaskAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, final View view, final int position) {
                if (view.getId() == R.id.bt_jiedan) {
                    new QMUIDialog.MessageDialogBuilder(getContext())
                            .setTitle("注意")
                            .setMessage("注意截止时间，任务须发布者同意或五分钟后自动同意，确定要接受吗？")
                            .addAction("取消", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }).addAction("确定", new QMUIDialogAction.ActionListener() {
                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            dialog.dismiss();
                            mPresenter.acceptTask(position);
                        }
                    }).create(mCurrentDialogStyle).show();
                }
            }
        });

        mTaskAdapter.setPreLoadNumber(2);
        mTaskAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.loadMore(10);
                    }
                }, 500);
            }
        }, mRecyclerView);
    }


    @Override
    public void showLoading() {
        mRefresh.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        mRefresh.setRefreshing(false);
    }

    @Override
    public void showSuccess(List<TaskEntity> data, TaskHallModel.FlagType type) {
        if (type != TaskHallModel.FlagType.LOAD_MORE) {
            mTaskAdapter.addData(0, data);
            mRecyclerView.scrollToPosition(0);
        } else
            mTaskAdapter.addData(data);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailure() {
        Toast.makeText(getContext(), "网络连接不稳定，请稍后重试", Toast.LENGTH_SHORT).show();
        mTaskAdapter.loadMoreFail();
    }

    @Override
    public void showAcceptTask() {
        Toast.makeText(getContext(), "请等待发布者同意", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showSuccess(List<TaskEntity> data) {

    }
}

