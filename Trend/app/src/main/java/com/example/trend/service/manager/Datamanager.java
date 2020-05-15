package com.example.trend.service.manager;

import android.content.Context;

import com.example.trend.service.Retrofit.RetrofitHelper;
import com.example.trend.service.Retrofit.RetrofitService;
import com.example.trend.service.entity.Developers;
import com.example.trend.service.entity.Languages_Collection;
import com.example.trend.service.entity.Repositories;
import com.example.trend.service.entity.Spoken_Languages_Collection;

import io.reactivex.Observable;

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context)
    {
        /*
        下面👇一行报错，我怀疑是context的问题，没有吧context传入我的mainactivity里，单例模式的问题
         */
        this.mRetrofitService= RetrofitHelper.getInstance(context).getServer();

    }
    public Observable<Repositories> getRepositories(){
        return mRetrofitService.getRepositories();
    }

    public Observable<Developers>getDevelopers(){
        return mRetrofitService.getDevelopers();
    }

    public Observable<Languages_Collection> getLanguages_Collection(){
        return mRetrofitService.getLanguages();
    }

    public Observable<Spoken_Languages_Collection> getSpoken_Languages_collection(){
        return mRetrofitService.getSpoken();
    }
}
