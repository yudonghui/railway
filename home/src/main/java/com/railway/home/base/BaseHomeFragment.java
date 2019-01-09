package com.railway.home.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.railway.common.BaseApp;
import com.railway.common.base.BasePresenter;
import com.railway.common.base.BaseView;
import com.railway.common.base.SimpleFragment;
import com.railway.common.inject.module.AppModule;
import com.railway.common.inject.module.FragmentModule;
import com.railway.home.component.DaggerFragmentHomeComponent;
import com.railway.home.component.FragmentHomeComponent;

import javax.inject.Inject;

/**
 * Created by codeest on 2016/8/2.
 * MVP Fragment基类
 */
public abstract class BaseHomeFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FragmentHomeComponent fragmentComponent = DaggerFragmentHomeComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .appModule(new AppModule(BaseApp.getContext()))
                .build();
        inject(fragmentComponent);
        if (mPresenter != null)
            mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected abstract void inject(FragmentHomeComponent fragmentComponent);

}