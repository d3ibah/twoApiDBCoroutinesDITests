package com.twcc.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twcc.data.db.DbUserEntity.Companion.USERS_TABLE_NAME

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg users: DbUserEntity)

    @Query("SELECT * FROM $USERS_TABLE_NAME")
    suspend fun getUsers(): List<DbUserEntity>
}