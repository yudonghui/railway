package com.railway.common.inject.module;

import android.content.Context;

import com.railway.common.inject.service.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule extends BaseMod{
    public ApiModule(Context application) {
        super(application);
    }

    @Provides
    public ApiService provideApiService() {
        return mRetrofit.create(ApiService.class);
    }
}
