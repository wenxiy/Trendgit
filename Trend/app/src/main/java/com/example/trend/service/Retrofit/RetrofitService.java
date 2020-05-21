package com.example.trend.service.Retrofit;

import com.example.trend.service.entity.Developers;
import com.example.trend.service.entity.Languages_Collection;
import com.example.trend.service.entity.Repositories;
import com.example.trend.service.entity.Spoken_Languages_Collection;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/developers")
    /*
    以下改动了一下去不解析数据直接处理Json数据
     */
    Observable<Developers> getDevelopers();
    @GET("/languages")
    Observable<Languages_Collection>getLanguages();
    @GET("/spoken_languages")
    Observable<Spoken_Languages_Collection>getSpoken();
    @GET("/repositories")
    Observable<Repositories>getRepositories();
}
