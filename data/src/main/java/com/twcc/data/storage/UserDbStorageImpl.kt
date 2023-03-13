package com.twcc.data.storage

import com.twcc.data.db.AppDatabase
import com.twcc.data.db.DbUserEntity
import com.twcc.domain.models.UserDomain

class UserDbStorageImpl(private val database: AppDatabase) : UserDbStorage {

    override suspend fun getUsers(): List<UserDomain> {
        return database.userDao().getUsers().mapDbUsersToDomainUsers()
    }

    override suspend fun addUsers(users: List<UserDomain>): Boolean {
        database.userDao().insertUsers(*users.mapUserDomainToDbUsersArray())
//        TODO: Add logic. Change return type to Result
        return true
    }

    private fun List<DbUserEntity>.mapDbUsersToDomainUsers() =
        this.map {
            UserDomain(
                id = it.id,
                name = it.name,
                api = it.api,
                image = it.image
            )
        }


    private fun List<UserDomain>.mapUserDomainToDbUsersArray() =
        this.map {
            DbUserEntity(
                id = it.id,
                name = it.name,
                api = it.api,
                image = it.image
            )
        }.toTypedArray()
}