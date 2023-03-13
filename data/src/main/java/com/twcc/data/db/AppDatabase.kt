package com.twcc.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbUserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}

class AppDatabaseImpl(private val context: Context) {

    companion object {
        private const val DB_NAME = "user_database"
    }

    val instance by lazy { getDbInstance() }
    private fun getDbInstance() =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).build()
}