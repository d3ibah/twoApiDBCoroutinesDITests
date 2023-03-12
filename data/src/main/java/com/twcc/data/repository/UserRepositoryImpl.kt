package com.twcc.data.repository

import com.twcc.data.storage.UserDbStorage
import com.twcc.data.storage.UserNetworkStorage
import com.twcc.domain.models.UserDomain
import com.twcc.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userNetworkStorage: UserNetworkStorage,
    private val userDbStorage: UserDbStorage
) : UserRepository {

    override suspend fun getUsers(): List<UserDomain> {
//      TODO: Add result handling
        val networkUsers = userNetworkStorage.getUsers()
        userDbStorage.addUsers(networkUsers)
        return userDbStorage.getUsers()
    }
}