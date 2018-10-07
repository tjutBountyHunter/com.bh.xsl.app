package org.yamu.tea_sc.sign.authentication;

import android.content.Context;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by mjt89 on 2018/10/3 0003
 */

public class AuthItemCreator {
    private static Context CONTEXT;

    public static final void initItems(QMUIGroupListView listVie, LinkedHashMap<AuthItemTag, AuthItemEntity> item) {

        item.clear();

        AuthItemEntity entity = new AuthItemEntity(1, AuthItemEntity.ItemType.EDIT_ITEM);
        entity.setmHint("请输入姓名");
        entity.setmError("请填入姓名");
        entity.setmTag(AuthItemTag.NAME);
        entity.setmTitle("姓名");
        QMUICommonListItemView itemView = listVie.createItemView(entity.getmTitle());
        itemView.setTag(entity.getmTag());
        itemView.setDetailText(entity.getmDetailText());
        itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        entity.setItemView(itemView);
        item.put(entity.getmTag(), entity);

        entity = new AuthItemEntity(2, AuthItemEntity.ItemType.CHECKABLE_ITEM);
        entity.setmTag(AuthItemTag.SEX);
        entity.setmTitle("性别");
        entity.setmChildItem("111");
        entity.setmChildItem("女");
        itemView = listVie.createItemView(entity.getmTitle());
        itemView.setTag(AuthItemTag.SEX);
        itemView.setDetailText(entity.getmDetailText());
        itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        entity.setItemView(itemView);
        item.put(entity.getmTag(), entity);

        entity = new AuthItemEntity(3, AuthItemEntity.ItemType.CHECKABLE_ITEM);
        entity.setmTag(AuthItemTag.SCHOOL);
        entity.setmTitle("学校");
        itemView = listVie.createItemView(entity.getmTitle());
        itemView.setTag(AuthItemTag.SCHOOL);
        itemView.setDetailText(entity.getmDetailText());
        itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        entity.setItemView(itemView);
        item.put(entity.getmTag(), entity);


        entity = new AuthItemEntity(4, AuthItemEntity.ItemType.CHECKABLE_ITEM);
        entity.setmTag(AuthItemTag.COLLEGE);
        entity.setmTitle("学院");
        itemView = listVie.createItemView(entity.getmTitle());
        itemView.setTag(AuthItemTag.COLLEGE);
        itemView.setDetailText(entity.getmDetailText());
        itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        entity.setItemView(itemView);
        item.put(entity.getmTag(), entity);

        entity = new AuthItemEntity(5, AuthItemEntity.ItemType.CHECKABLE_ITEM);
        entity.setmTag(AuthItemTag.MAJOR);
        entity.setmTitle("专业");
        itemView = listVie.createItemView(entity.getmTitle());
        itemView.setTag(AuthItemTag.MAJOR);
        itemView.setDetailText(entity.getmDetailText());
        itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        entity.setItemView(itemView);
        item.put(entity.getmTag(), entity);

        entity = new AuthItemEntity(6, AuthItemEntity.ItemType.EDIT_ITEM);
        entity.setmHint("请输入学号");
        entity.setmError("请填入学号");
        entity.setmTag(AuthItemTag.SCHOOL_NUMBER);
        entity.setmTitle("学号");
        itemView = listVie.createItemView(entity.getmTitle());
        itemView.setTag(entity.getmTag());
        itemView.setDetailText(entity.getmDetailText());
        itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        entity.setItemView(itemView);
        item.put(entity.getmTag(), entity);
    }
}
