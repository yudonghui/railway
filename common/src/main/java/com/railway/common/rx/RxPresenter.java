package com.railway.common.rx;

import com.railway.common.base.BaseModel;
import com.railway.common.base.BasePresenter;
import com.railway.common.base.BaseView;

import javax.inject.Inject;

public class RxPresenter<T extends BaseView, V extends BaseModel> implements BasePresenter<T, V> {
    @Inject
    protected V mModel;

    protected T mView;

    public RxPresenter() {
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }
}
