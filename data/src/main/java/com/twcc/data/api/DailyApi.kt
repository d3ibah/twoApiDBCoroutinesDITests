package com.twcc.data.api

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DailyApi {

    val retrofit = createRetrofit().create(DailyService::class.java)

    private const val BASE_URL = "https://api.dailymotion.com/"

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(getGsonConverter())
            .build()
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).build()
    }

    private fun getLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun getGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }
}