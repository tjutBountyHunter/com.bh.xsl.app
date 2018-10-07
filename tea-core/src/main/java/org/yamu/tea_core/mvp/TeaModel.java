package org.yamu.tea_core.mvp;

import org.yamu.tea_core.util.storage.TeaPreference;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public class TeaModel<C extends IModelCallback> {
    protected C callback = null;

    public TeaModel(C callback) {
        this.callback = callback;
    }
}
