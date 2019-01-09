package com.railway.home.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.railway.common.bean.User;
import com.railway.common.inject.service.ApiService;
import com.railway.home.R;
import com.railway.home.base.BaseHomeFragment;
import com.railway.home.component.FragmentHomeComponent;
import com.railway.home.presenter.HomePresenter;

import java.util.HashMap;

import javax.inject.Inject;

public class HomeFragment extends BaseHomeFragment<HomePresenter> {
    @Inject
    ApiService mApiService;
    private TextView mTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mTextView = mView.findViewById(R.id.home_network);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("page", "1");
                params.put("sort", "0");
                params.put("type", "glean");
                params.put("limit", "5");
                mPresenter.web(params);
            }
        });
    }

    public void setData(User user) {
        Log.e("结果", user.toString());
    }

    @Override
    protected void inject(FragmentHomeComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
