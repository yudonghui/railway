package com.railway.user.component;

import com.railway.common.inject.module.ActivityModule;
import com.railway.common.inject.module.ApiModule;
import com.railway.user.activity.LoginActivity;

import dagger.Component;

@Component(modules = {ActivityModule.class, ApiModule.class})
public interface ActivityUserComponent {
    void inject(LoginActivity loginActivity);
}
