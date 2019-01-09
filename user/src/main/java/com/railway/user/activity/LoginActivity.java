package com.railway.user.activity;

import android.util.Log;
import android.view.View;

import com.railway.common.bean.User;
import com.railway.common.inject.service.ApiService;
import com.railway.user.R;
import com.railway.user.base.BaseUserActivity;
import com.railway.user.component.ActivityUserComponent;
import com.railway.user.presenter.LoginPrensenter;

import java.util.HashMap;

import javax.inject.Inject;

public class LoginActivity extends BaseUserActivity<LoginPrensenter> {

    @Inject
    ApiService mApiService;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }
    public void mvp(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("sort", "0");
        params.put("type", "glean");
        params.put("limit", "5");
        mPresenter.web(params);
    }

    public void setData(User user) {
        Log.e("结果", user.toString());
    }

    @Override
    public void inject(ActivityUserComponent activityComponent) {
        activityComponent.inject(this);
    }


}
