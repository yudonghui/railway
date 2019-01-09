package com.railway.common.inject.module;

import android.content.Context;

import com.railway.common.inject.service.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule extends BaseMod{
    public AppModule(Context application) {
        super(application);
    }

    @Provides
    public ApiService provideApiService() {
        return mRetrofit.create(ApiService.class);
    }
}
