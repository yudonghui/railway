package com.railway.common.base;

public interface BasePresenter<T extends BaseView,V> {
    void attachView(T view);

    void detachView();
}
