package org.yamu.tea_core.mvp;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public abstract class TeaPresenter<T, M extends TeaModel, V extends ITeaView> implements IModelCallback<T> {
    protected V view = null;
    protected M mModel = getModel();


    public void attchView(V view) {
        this.view = view;
    }

    protected abstract M getModel();

    public void detachView(V view) {
        this.view = null;
    }

    public boolean isAttched() {
        return this.view != null;
    }
}
