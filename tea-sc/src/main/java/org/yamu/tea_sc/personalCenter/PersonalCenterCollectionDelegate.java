package org.yamu.tea_sc.personalCenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;

import java.util.List;


public class PersonalCenterCollectionDelegate extends TeaDelegate {
    private static PersonalCenterCollectionDelegate collection;


    private List<HunterCollection> mHunters;
    private List<TaskCollection> mTasks;

    public static Fragment newInstance() {
        if (collection == null)
            collection = new PersonalCenterCollectionDelegate();
        return collection;
    }

    private void initData() {

    }


    private void initView() {

    }

    private void initRecyclerView() {

    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }

    };

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_center_collection;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
        initData();
        initRecyclerView();
    }

//    class HunterAdapter extends BaseRecyclerAdapter<HunterCollection> {
//        private Button button;
//
//        public HunterAdapter(Context ctx, List<HunterCollection> list) {
//            super(ctx, list);
//        }
//
//        @Override
//        public int getItemLayoutId(int viewType) {
//            return R.layout.item_hunter_collection;
//        }
//
//        @Override
//        public void bindData(RecyclerViewHolder holder, int position, HunterCollection item) {
//            button = holder.getButton(R.id.bt_collection);
//            if (position % 2 == 0) {
//                button.setText("已收藏");
//                button.setSelected(true);
//            } else {
//                button.setText("+ 收藏");
//                button.setSelected(false);
//            }
//
//        }
//    }
//
//    class TaskAdapter extends BaseRecyclerAdapter<TaskCollection> {
//
//
//        public TaskAdapter(Context ctx, List<TaskCollection> list) {
//            super(ctx, list);
//        }
//
//        @Override
//        public int getItemLayoutId(int viewType) {
//            return R.layout.item_task_collection;
//        }
//
//        @Override
//        public void bindData(RecyclerViewHolder holder, int position, TaskCollection item) {
//
//        }
//    }
//
//    class GroupClickListener implements ExpandableListView.OnGroupClickListener {
//        @Override
//        public boolean onGroupClick(ExpandableListView parent, View v,
//                                    int groupPosition, long id) {
//            if (expandFlag == -1) {
//                // 展开被选的group
//                explistview.expandGroup(groupPosition);
//                // 设置被选中的group置于顶端
//                explistview.setSelectedGroup(groupPosition);
//                expandFlag = groupPosition;
//            } else if (expandFlag == groupPosition) {
//                explistview.collapseGroup(expandFlag);
//                expandFlag = -1;
//            } else {
//                explistview.collapseGroup(expandFlag);
//                // 展开被选的group
//                explistview.expandGroup(groupPosition);
//                // 设置被选中的group置于顶端
//                explistview.setSelectedGroup(groupPosition);
//                expandFlag = groupPosition;
//            }
//            return true;
//        }
//    }
}

