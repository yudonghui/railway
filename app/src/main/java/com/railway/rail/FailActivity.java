package com.railway.rail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.railway.common.AppManager;
import com.railway.common.bean.User;
import com.railway.common.utils.NetWorkUtils;
import com.railway.home.fragment.HomeFragment;
import com.railway.rail.component.ActivityAppComponent;
import com.railway.rail.presenter.FailPresenter;
import com.railway.rail.widget.BottomBar;
import com.railway.rail.widget.BottomBarTab;
import com.railway.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FailActivity extends BaseActivity<FailPresenter> {


    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    @BindView(R.id.no_net)
    TextView mNoNet;

    //    private BottomBar mBottomBar;
//    private TextView mNoNet;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFm;
    private long firstTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fail;
    }

    @Override
    protected void initView() {
        initBottomBar();
        initFragments();
        initListener();
        mBottomBar.setCurrentItem(0);
        showHideFragment(0, -1);
    }

    @Override
    public void onNetChange(int netMobile) {
        super.onNetChange(netMobile);
        //网络状态变化时的操作
        if (netMobile == NetWorkUtils.NETWORK_NONE) {
            mNoNet.setVisibility(View.VISIBLE);
        } else {
            mNoNet.setVisibility(View.GONE);
        }
    }

    public void tiaozhuan(View view) {
        try {
            Class<?> aClass = Class.forName("com.railway.user.activity.LoginActivity");
            startActivity(aClass);
        } catch (Exception e) {
            Toast.makeText(this, "没有找到LoginActivity", Toast.LENGTH_SHORT).show();
        }
    }

    private void initFragments() {
        HomeFragment homeFragment = new HomeFragment();
        UserFragment userFragment = new UserFragment();
        mFragments.add(homeFragment);
        mFragments.add(userFragment);
        mFm = getSupportFragmentManager();
        mFm.beginTransaction()
                .add(R.id.frame_layout, homeFragment)
                .add(R.id.frame_layout, userFragment)
                .hide(homeFragment)
                .hide(userFragment)
                .commitAllowingStateLoss();
    }

    private void initListener() {
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(position, prePosition);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private static final int NO_PRE = -1;

    private void showHideFragment(int position, int prePosition) {
        if (prePosition == NO_PRE) {
            mFm.beginTransaction()
                    .show(mFragments.get(position))
                    .commitAllowingStateLoss();
        } else {
            mFm.beginTransaction()
                    .show(mFragments.get(position))
                    .hide(mFragments.get(prePosition))
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1500) {// 如果两次按键时间间隔大于800毫秒，则不退出
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                firstTime = secondTime;// 更新firstTime
                return true;
            } else {
                AppManager.getAppManager().AppExit(this, false);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void inject(ActivityAppComponent activityComponent) {
        activityComponent.inject(this);
    }

    private void initBottomBar() {
        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.loan_normal, "home"))
                .addItem(new BottomBarTab(this, R.mipmap.mall_normal, "user"));
    }

    public void setData(User user) {

    }

}
