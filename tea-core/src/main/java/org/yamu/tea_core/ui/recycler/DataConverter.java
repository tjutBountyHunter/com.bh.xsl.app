package org.yamu.tea_core.ui.recycler;

import java.util.ArrayList;

/**
 * Created by mjt89 on 2018/10/2 0002
 */

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();

    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

}
