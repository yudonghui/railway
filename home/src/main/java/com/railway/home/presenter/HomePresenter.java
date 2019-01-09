package com.railway.home.presenter;

import android.widget.Toast;

import com.railway.common.bean.User;
import com.railway.common.common.CommonSubscriber;
import com.railway.common.rx.RxPresenter;
import com.railway.common.utils.ApiException;
import com.railway.home.fragment.HomeFragment;
import com.railway.home.module.HomeModule;

import java.util.HashMap;

import javax.inject.Inject;

public class HomePresenter extends RxPresenter<HomeFragment, HomeModule> {
    @Inject
    public HomePresenter() {
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
                Toast.makeText(mView.getActivity(), e.getMsg(), Toast.LENGTH_SHORT).show();
                mView.cancelLoadingDialog();
            }
        });
    }
}
