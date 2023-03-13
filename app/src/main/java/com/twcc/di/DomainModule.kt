package com.twcc.di

import com.twcc.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetUsersUseCase> {
        GetUsersUseCase(userRepository = get())
    }
}