package com.railway.rail.component;

import com.railway.common.inject.module.ActivityModule;
import com.railway.common.inject.module.ApiModule;
import com.railway.rail.FailActivity;

import dagger.Component;

@Component(modules = {ActivityModule.class, ApiModule.class})
public interface ActivityAppComponent {
    void inject(FailActivity loginActivity);
}
