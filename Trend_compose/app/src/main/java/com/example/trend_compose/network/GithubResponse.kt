package com.example.trend_compose.network

import com.google.gson.annotations.SerializedName

data class GithubResponse(
    val items: List<GithubRepo>
)

data class GithubRepo(


    @SerializedName("full_name")
    val name: String,

    val description: String?,

    @SerializedName("stargazers_count")
    val stars: Int,

    @SerializedName("html_url")
    val url: String,

    val owner: Owner
)

data class Owner(
    val login: String,

    @SerializedName("avatar_url")
    val avatar_url: String
)