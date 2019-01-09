package com.railway.user.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.railway.common.bean.User;
import com.railway.common.inject.service.ApiService;
import com.railway.user.R;
import com.railway.user.base.BaseUserFragment;
import com.railway.user.component.FragmentUserComponent;
import com.railway.user.presenter.UserPresenter;

import java.util.HashMap;

import javax.inject.Inject;

public class UserFragment extends BaseUserFragment<UserPresenter> {
    @Inject
    ApiService mApiService;
    private TextView mTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        mTextView = mView.findViewById(R.id.user_network);
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
    protected void inject(FragmentUserComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
