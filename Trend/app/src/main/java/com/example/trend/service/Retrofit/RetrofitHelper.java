package com.example.trend.service.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static volatile RetrofitService service;

    public static RetrofitService getService() {
        if (service == null) {
            synchronized (RetrofitHelper.class) {
                if (service == null) {
                    service = new Retrofit.Builder()
                            .baseUrl("https://ghapi.huchen.dev")
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(RetrofitService.class);
                }
            }
            return service;
        }
        return service;
    }
}
