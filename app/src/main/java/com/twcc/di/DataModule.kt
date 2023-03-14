package com.twcc.di

import com.twcc.data.api.DailyService
import com.twcc.data.api.GitHubService
import com.twcc.data.api.RetrofitApi
import com.twcc.data.api.RetrofitApi.Companion.DAILY_BASE_URL
import com.twcc.data.api.RetrofitApi.Companion.GITHUB_BASE_URL
import com.twcc.data.db.AppDatabase
import com.twcc.data.db.AppDatabaseImpl
import com.twcc.data.repository.UserRepositoryImpl
import com.twcc.data.storage.UserDbStorage
import com.twcc.data.storage.UserDbStorageImpl
import com.twcc.data.storage.UserNetworkStorage
import com.twcc.data.storage.UserNetworkStorageImpl
import com.twcc.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<AppDatabase> {
        AppDatabaseImpl(context = get()).instance
    }

    single<UserDbStorage> {
        UserDbStorageImpl(database = get())
    }

    single<UserNetworkStorage> {
        UserNetworkStorageImpl(
            gitHubApi = RetrofitApi(GITHUB_BASE_URL, GitHubService::class.java),
            dailyApi = RetrofitApi(DAILY_BASE_URL, DailyService::class.java)
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
            userNetworkStorage = get(),
            userDbStorage = get()
        )
    }
}