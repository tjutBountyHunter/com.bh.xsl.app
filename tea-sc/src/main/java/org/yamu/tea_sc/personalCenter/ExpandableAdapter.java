package org.yamu.tea_sc.personalCenter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    public String[] groups;
    public String[][] children;
    private Context context;

    public ExpandableAdapter(Context context, String[] groups, String[][] children) {
        this.groups = groups;
        this.children = children;
        this.context = context;
    }

    //获取与给定的组相关的数据，得到数组groups中元素的数据
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    //获取与孩子在给定的组相关的数据,得到数组children中元素的数据
    public Object getChild(int groupPosition, int childPosition) {
        return children[groupPosition][childPosition];
    }

    //获取的群体数量，得到groups里元素的个数
    public int getGroupCount() {
        return groups.length;
    }

    //取得指定组中的children个数，就是groups中每一个条目中的个数
    public int getChildrenCount(int groupPosition) {
        return children[groupPosition].length;
    }

    //获取组在给定的位置编号，即groups中元素的ID
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取在给定的组的children的ID，也就是children中元素的ID
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //获取一个视图显示给定组，存放groups
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        TextView textView = getGenericView(24);
        textView.setText(getGroup(groupPosition).toString());
        return textView;
    }

    //获取一个视图显示在给定的组 的儿童的数据，就是存放children
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        TextView textView = getGenericView(18);
        textView.setText(getChild(groupPosition, childPosition).toString());
        return textView;
    }

    //孩子在指定的位置是可选的，即：children中的元素是可点击的
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //表示孩子是否和组ID是跨基础数据的更改稳定
    public boolean hasStableIds() {
        return true;
    }


    //自定义的创建TextView
    public TextView getGenericView(int mTextSize) {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(context);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setPadding(42, 12, 12, 12);
        textView.setTextSize(mTextSize);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

}

