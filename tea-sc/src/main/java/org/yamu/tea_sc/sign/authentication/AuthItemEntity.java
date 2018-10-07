package org.yamu.tea_sc.sign.authentication;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by mjt89 on 2018/10/3 0003
 */

public class AuthItemEntity {

    private int id = 0;
    private AuthItemTag mTag = null;
    private String mTitle = null;
    private String mDetailText = "未填";
    private String mError = null;
    private ItemType mType = ItemType.CHECKABLE_ITEM;
    private String mHint = null;
    private List<String> mChildItems = null;
    private QMUICommonListItemView itemView = null;

    public AuthItemEntity(int id, ItemType type) {
        if (type == ItemType.CHECKABLE_ITEM)
            mChildItems = new ArrayList<>();
        this.id = id;
    }

    public QMUICommonListItemView getItemView() {
        return itemView;
    }

    public void setItemView(QMUICommonListItemView itemView) {
        this.itemView = itemView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthItemTag getmTag() {
        return mTag;
    }

    public void setmTag(AuthItemTag mTag) {
        this.mTag = mTag;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDetailText() {
        return mDetailText;
    }

    public void setmDetailText(String mDetailText) {
        this.mDetailText = mDetailText;
    }

    public String getmError() {
        return mError;
    }

    public void setmError(String mError) {
        this.mError = mError;
    }

    public ItemType getmType() {
        return mType;
    }

    public void setmType(ItemType mType) {
        this.mType = mType;
    }

    public String getmHint() {
        return mHint;
    }

    public void setmHint(String mHint) {
        this.mHint = mHint;
    }

    public List<String> getmChildItems() {
        if (mChildItems != null)
            return mChildItems;
        return null;
    }

    public void setmChildItems(List<String> mChildItems) {
        if (mChildItems != null)
            this.mChildItems.addAll(mChildItems);
    }

    public void setmChildItem(String item) {
        if (mChildItems != null)
            this.mChildItems.add(item);
    }

    public enum ItemType {
        EDIT_ITEM,
        CHECKABLE_ITEM,
        CUSTOM
    }
}
