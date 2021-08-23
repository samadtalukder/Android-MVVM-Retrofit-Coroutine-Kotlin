package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter

@Database(entities = [ResultTable::class], version = 1)

abstract class UserDB : RoomDatabase() {
    abstract fun getUsersDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDB::class.java,
            "user_db.db"
        ).build()
    }
}