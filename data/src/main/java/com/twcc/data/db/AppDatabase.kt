package com.twcc.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbUserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}

object Database {
    //    TODO: fix it
    fun getDb(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
}