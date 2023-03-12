package com.twcc.data.storage

import com.twcc.domain.models.UserDomain

class UserDbStorageImpl : UserDbStorage {

    override suspend fun getUsers(): List<UserDomain> {
        val users = mutableListOf<UserDomain>()
//        TODO: Add logic
        return users
    }

    override suspend fun addUsers(users: List<UserDomain>): Boolean {
//        TODO: Add logic. Change return type to Result
        return true
    }
}