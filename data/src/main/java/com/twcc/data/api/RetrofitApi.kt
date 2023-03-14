package com.twcc.data.api

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi<T>(private val baseUrl: String, private val serviceClass: Class<T>) {

    companion object {
        const val DAILY_BASE_URL = "https://api.dailymotion.com/"
        const val GITHUB_BASE_URL = "https://api.github.com/"
    }

    fun service(): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(getGsonConverter())
            .build()
        return retrofit.create(serviceClass)
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