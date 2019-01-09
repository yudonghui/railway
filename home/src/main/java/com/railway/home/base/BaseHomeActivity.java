package com.railway.home.base;

import android.widget.Toast;

import com.railway.common.BaseApp;
import com.railway.common.base.BasePresenter;
import com.railway.common.base.BaseView;
import com.railway.common.base.SimpleActivity;
import com.railway.common.inject.module.ActivityModule;
import com.railway.common.inject.module.ApiModule;
import com.railway.home.component.ActivityHomeComponent;
import com.railway.home.component.DaggerActivityHomeComponent;

import javax.inject.Inject;

public abstract class BaseHomeActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    protected T mPresenter;

    @Override
    protected void init() {
        super.init();
        ActivityHomeComponent activityComponent = DaggerActivityHomeComponent.builder()
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
    public abstract void inject(ActivityHomeComponent activityComponent);

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
