package com.twcc.data.storage

import com.twcc.domain.models.UserDomain

interface Storage {

    suspend fun getUsers(): List<UserDomain>
}