package com.railway.home.component;

import com.railway.common.inject.module.AppModule;
import com.railway.common.inject.module.FragmentModule;
import com.railway.home.fragment.HomeFragment;

import dagger.Component;

@Component(modules = {FragmentModule.class, AppModule.class})
public interface FragmentHomeComponent {
    void inject(HomeFragment homeFragment);
}
