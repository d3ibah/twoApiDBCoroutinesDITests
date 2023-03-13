package com.twcc.di

import com.twcc.data.db.AppDatabase
import com.twcc.data.db.Database
import com.twcc.data.repository.UserRepositoryImpl
import com.twcc.data.storage.UserDbStorage
import com.twcc.data.storage.UserDbStorageImpl
import com.twcc.data.storage.UserNetworkStorage
import com.twcc.data.storage.UserNetworkStorageImpl
import com.twcc.domain.repository.UserRepository
import com.twcc.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val dataModule = module {

    single<AppDatabase> {
        Database.getDb(context = get())
    }

    single<UserDbStorage> {
        UserDbStorageImpl(database = get())
    }

    single<UserNetworkStorage> {
        UserNetworkStorageImpl()
    }

    single<UserRepository> {
        UserRepositoryImpl(
            userNetworkStorage = get(),
            userDbStorage = get()
        )
    }

    single<GetUsersUseCase> {
        GetUsersUseCase(get())
    }
}