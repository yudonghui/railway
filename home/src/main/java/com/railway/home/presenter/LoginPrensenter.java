package com.railway.home.presenter;

import android.widget.Toast;

import com.railway.common.bean.User;
import com.railway.common.common.CommonSubscriber;
import com.railway.common.rx.RxPresenter;
import com.railway.common.utils.ApiException;
import com.railway.home.activity.LoginActivity;
import com.railway.home.module.LoginModule;

import java.util.HashMap;

import javax.inject.Inject;

public class LoginPrensenter extends RxPresenter<LoginActivity, LoginModule> {
    @Inject
    public LoginPrensenter() {
    }

    public void web(HashMap<String, String> params) {
        mView.showLoadingDialog();
        mModel.web(params, new CommonSubscriber<User>() {
            @Override
            public void getData(User user) {
                mView.cancelLoadingDialog();
                mView.setData(user);
            }

            @Override
            public void error(ApiException e) {
                Toast.makeText(mView, e.getMsg(), Toast.LENGTH_SHORT).show();
                mView.cancelLoadingDialog();
            }
        });
    }
}
