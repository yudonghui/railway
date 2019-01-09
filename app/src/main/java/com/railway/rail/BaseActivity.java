package com.railway.rail;

import android.widget.Toast;

import com.railway.common.BaseApp;
import com.railway.common.base.BasePresenter;
import com.railway.common.base.BaseView;
import com.railway.common.base.SimpleActivity;
import com.railway.common.inject.module.ActivityModule;
import com.railway.common.inject.module.ApiModule;
import com.railway.rail.component.ActivityAppComponent;
import com.railway.rail.component.DaggerActivityAppComponent;

import javax.inject.Inject;

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    protected T mPresenter;

    @Override
    protected void init() {
        super.init();
        ActivityAppComponent activityAppComponent= DaggerActivityAppComponent
       .builder()
                .activityModule(new ActivityModule(this))
                .apiModule(new ApiModule(BaseApp.getContext()))
                .build();
        inject(activityAppComponent);
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    /**
     * 注入
     */
    public abstract void inject(ActivityAppComponent activityComponent);

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
