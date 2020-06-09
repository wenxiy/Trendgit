package com.example.trend.service.Retrofit;

import com.example.trend.service.model.Developers;

import io.reactivex.Observable;

public class Retrofitmanager {
    public static Observable<Developers> getDevelopers()
    {
        return RetrofitHelper.getService().getDevelopers();
    }
}
