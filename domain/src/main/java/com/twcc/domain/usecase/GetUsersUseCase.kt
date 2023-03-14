package com.twcc.domain.usecase

import com.twcc.domain.models.UserDomain
import com.twcc.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {

    suspend fun execute(): List<UserDomain> {
        return userRepository.getUsers()
    }
}