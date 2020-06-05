package com.example.trend.service.Retrofit;
import android.content.Context;

import com.example.trend.BuildConfig;
import com.example.trend.service.entity.Developers;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//单例模式，提高内存利用和效率
//取消单例模式
public class RetrofitHelper {
   private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://ghapi.huchen.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
   private final static  RetrofitService retrofitservice=retrofit.create(RetrofitService.class);
   public static Observable<Developers> getDevelopers()
    {
        return retrofitservice.getDevelopers();
    }
}
