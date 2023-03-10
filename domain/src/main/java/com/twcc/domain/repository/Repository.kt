package com.twcc.domain.repository

import com.twcc.domain.models.UserDomain

interface Repository {

    suspend fun getUsers(): List<UserDomain>
}