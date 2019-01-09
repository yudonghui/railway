package com.railway.home.component;

import com.railway.common.inject.module.ActivityModule;
import com.railway.common.inject.module.ApiModule;
import com.railway.home.activity.LoginActivity;

import dagger.Component;

@Component(modules = {ActivityModule.class, ApiModule.class})
public interface ActivityHomeComponent {
    void inject(LoginActivity loginActivity);
}
