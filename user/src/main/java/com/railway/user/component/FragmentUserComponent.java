package com.railway.user.component;

import com.railway.common.inject.module.AppModule;
import com.railway.common.inject.module.FragmentModule;
import com.railway.user.fragment.UserFragment;

import dagger.Component;

@Component(modules = {FragmentModule.class, AppModule.class})
public interface FragmentUserComponent {
    void inject(UserFragment userFragment);
}
