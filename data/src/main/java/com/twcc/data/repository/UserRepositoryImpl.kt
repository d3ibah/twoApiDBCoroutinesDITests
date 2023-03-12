package com.twcc.data.repository

import com.twcc.data.storage.UserNetworkStorage
import com.twcc.domain.models.UserDomain
import com.twcc.domain.repository.UserRepository

class UserRepositoryImpl(private val userNetworkStorage: UserNetworkStorage) : UserRepository {

    override suspend fun getUsers(): List<UserDomain> {
        return userNetworkStorage.getUsers()
    }
}