package com.example.trend_compose.network

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun searchTopRepos(
        @Query("q") query: String = "stars:>10000",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int
    ): GithubResponse
}