package com.example.trend.service.Retrofit;

import com.example.trend.ui.model.Repository;

import java.util.List;

import io.reactivex.Observable;

public class Retrofitmanager {
    public static Observable<List<Repository>> getDevelopers()
    {
        return RetrofitHelper.getService().getDevelopers();
    }
}
