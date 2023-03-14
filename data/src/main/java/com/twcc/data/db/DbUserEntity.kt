package com.twcc.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.twcc.data.db.DbUserEntity.Companion.USERS_TABLE_NAME

@Entity(tableName = USERS_TABLE_NAME)
data class DbUserEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "api") val api: String,
    @ColumnInfo(name = "image") val image: String
) {

    companion object {
        const val USERS_TABLE_NAME = "users"
    }
}
