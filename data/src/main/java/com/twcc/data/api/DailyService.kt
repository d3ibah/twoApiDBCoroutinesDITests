package com.twcc.data.api

import retrofit2.http.GET

interface DailyService {

    @GET("users")
    suspend fun getUsers(): DailyUserResponse
}