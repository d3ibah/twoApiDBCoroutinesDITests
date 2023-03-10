package com.twcc.data.repository

import com.twcc.data.storage.Storage
import com.twcc.domain.models.UserDomain
import com.twcc.domain.repository.Repository

class RepositoryImpl(private val storage: Storage) : Repository {

    override suspend fun getUsers(): List<UserDomain> {
        return storage.getUsers()
    }
}