package com.railway.user.base;

import android.widget.Toast;

import com.railway.common.BaseApp;
import com.railway.common.base.BasePresenter;
import com.railway.common.base.BaseView;
import com.railway.common.base.SimpleActivity;
import com.railway.common.inject.module.ActivityModule;
import com.railway.common.inject.module.ApiModule;
import com.railway.user.component.ActivityUserComponent;
import com.railway.user.component.DaggerActivityUserComponent;

import javax.inject.Inject;

public abstract class BaseUserActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    protected T mPresenter;

    @Override
    protected void init() {
        super.init();
        ActivityUserComponent activityComponent = DaggerActivityUserComponent.builder()
                .activityModule(new ActivityModule(this))
                .apiModule(new ApiModule(BaseApp.getContext()))
                .build();
        inject(activityComponent);
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    /**
     * 注入
     */
    public abstract void inject(ActivityUserComponent activityComponent);

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
