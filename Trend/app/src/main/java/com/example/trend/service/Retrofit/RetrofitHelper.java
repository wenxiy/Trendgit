package com.example.trend.service.Retrofit;

import com.example.trend.service.model.Developers;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//单例模式，提高内存利用和效率
//取消单例模式
public class RetrofitHelper {
    public static volatile RetrofitService service;
    public static RetrofitService getService(){
        if(service==null) {
            synchronized (RetrofitHelper.class){
                if(service==null){
                    service=new Retrofit.Builder()
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("https://ghapi.huchen.dev/")
                            .build()
                            .create(RetrofitService.class);
                }
            }
            return service;
        }
        return service;
    }
   public static Observable<Developers> getDevelopers()
    {
        return service.getDevelopers();
    }
}
