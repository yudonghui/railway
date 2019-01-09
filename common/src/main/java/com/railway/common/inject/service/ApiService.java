package com.railway.common.inject.service;


import com.railway.common.bean.User;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET("v1/cms/getlist")
    Flowable<User> getUser(@QueryMap Map<String, String> params);
}
