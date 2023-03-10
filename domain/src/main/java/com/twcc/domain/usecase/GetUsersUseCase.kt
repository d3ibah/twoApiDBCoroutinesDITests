package com.twcc.domain.usecase

import com.twcc.domain.models.UserDomain
import com.twcc.domain.repository.Repository

class GetUsersUseCase(private val repository: Repository) {

    suspend fun execute(): List<UserDomain> {
        return repository.getUsers()
    }
}