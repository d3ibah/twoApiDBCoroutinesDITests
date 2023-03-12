package com.twcc.domain.repository

import com.twcc.domain.models.UserDomain

interface UserRepository {

    suspend fun getUsers(): List<UserDomain>
}