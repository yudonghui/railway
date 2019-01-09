package com.railway.user.module;

import com.railway.common.base.BaseModel;
import com.railway.common.bean.User;
import com.railway.common.common.CommonSubscriber;
import com.railway.common.inject.service.ApiService;
import com.railway.common.rx.HttpRxObservable;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class LoginModule extends BaseModel {
    private final RxAppCompatActivity mActivity;
    @Inject
    ApiService mApiService;

    @Inject
    public LoginModule(RxAppCompatActivity activity) {
        mActivity = activity;
    }
    public void web(HashMap<String, String> params, CommonSubscriber<User> subscriber){
        Flowable<User> userFlowable = mApiService.getUser(params);
        //被观察者
        Flowable observable = HttpRxObservable.getObservable(userFlowable, mActivity);
        observable.subscribe(subscriber);
    }
}
