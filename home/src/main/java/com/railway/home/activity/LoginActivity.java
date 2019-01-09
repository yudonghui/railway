package com.railway.home.activity;

import android.util.Log;
import android.view.View;

import com.railway.common.bean.User;
import com.railway.common.inject.service.ApiService;
import com.railway.home.R;
import com.railway.home.base.BaseHomeActivity;
import com.railway.home.component.ActivityHomeComponent;
import com.railway.home.presenter.LoginPrensenter;

import java.util.HashMap;

import javax.inject.Inject;

public class LoginActivity extends BaseHomeActivity<LoginPrensenter> {

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
    public void inject(ActivityHomeComponent activityComponent) {
        activityComponent.inject(this);
    }


}
