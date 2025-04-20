package com.example.trend.service.Retrofit;

import com.example.trend.ui.model.Repository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/repositories")
    Observable<List<Repository>> getDevelopers();
}
