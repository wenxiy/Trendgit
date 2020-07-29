package com.example.trend.service.Retrofit;

import com.example.trend.service.model.Developers;
import com.example.trend.service.model.Languages_Collection;
import com.example.trend.service.model.Repositories;
import com.example.trend.service.model.Spoken_Languages_Collection;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/developers")
    Observable<List<Developers>> getDevelopers();
    @GET("/languages")
    Observable<Languages_Collection>getLanguages();
    @GET("/spoken_languages")
    Observable<Spoken_Languages_Collection>getSpoken();
    @GET("/repositories")
    Observable<Repositories>getRepositories();
}
