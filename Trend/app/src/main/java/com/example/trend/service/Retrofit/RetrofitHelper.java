package com.example.trend.service.Retrofit;
import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//单例模式，提高内存利用和效率
public class RetrofitHelper {
    private Context mCntext;//我怀疑是这个context的问题，
    OkHttpClient client=new OkHttpClient();//这里报错
    private Retrofit api=null;
    public Retrofit getApi()
    {
        return api;
    }
    private static RetrofitHelper instance=null;
    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (RetrofitHelper.class){
                if (instance==null) {
                    instance=new RetrofitHelper(context);

                }
            }
        }//线程不安全,后续加死锁
        //目前已加
        return instance;
    }
    public RetrofitHelper(Context context) {
        mCntext=context;
        init();
    }
    private void init()
    {
        resetAPP();
    }

    private void resetAPP() {
        api=new Retrofit.Builder()
                .baseUrl("https://ghapi.huchen.dev")//这里出错报错，仔细查看url
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    //
    public RetrofitService getServer(){
        return api.create(RetrofitService.class);
    }

}
