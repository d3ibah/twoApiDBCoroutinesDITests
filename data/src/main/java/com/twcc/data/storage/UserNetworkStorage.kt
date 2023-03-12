package com.twcc.data.storage

import com.twcc.domain.models.UserDomain

interface UserNetworkStorage {

    suspend fun getUsers(): List<UserDomain>
}