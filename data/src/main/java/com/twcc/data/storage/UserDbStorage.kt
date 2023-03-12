package com.twcc.data.storage

import com.twcc.domain.models.UserDomain

interface UserDbStorage {

    suspend fun getUsers(): List<UserDomain>

    suspend fun addUsers(users: List<UserDomain>): Boolean

}