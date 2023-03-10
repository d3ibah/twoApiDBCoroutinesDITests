package com.twcc.data.api

import retrofit2.http.GET

interface GitHubService {

    @GET("users")
    suspend fun getUsers(): List<GitHubUserResponse>
}