package com.jimmy.avowsstore.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jimmy.avowsstore.data.local.dao.UserDao
import com.jimmy.avowsstore.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
)
abstract class BaseDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}