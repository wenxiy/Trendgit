package com.example.trend.service.Retrofit;

import com.example.trend.service.model.Developers;

import java.util.List;

import io.reactivex.Observable;

public class Retrofitmanager {
    public static Observable<List<Developers>> getDevelopers()
    {
        return RetrofitHelper.getService().getDevelopers();
    }
}
