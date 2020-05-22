package com.example.trend.service.Retrofit;
import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//单例模式，提高内存利用和效率
//取消单例模式
public class RetrofitHelper {
    private static Retrofit retrofit = null;
    private final static  String baseurl="https://ghapi.huchen.dev";
    public Retrofit getRetrofit() {
        return retrofit;
    }
    public RetrofitHelper()
    {
        getClient();
    }
    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseurl)//这里出错报错，仔细查看url
                    .build();
        }
        return retrofit;
    }
}
