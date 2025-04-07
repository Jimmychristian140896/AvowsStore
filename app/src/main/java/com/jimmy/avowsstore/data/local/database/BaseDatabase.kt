package com.jimmy.avowsstore.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jimmy.avowsstore.data.local.dao.TransactionDao
import com.jimmy.avowsstore.data.local.entity.TransactionEntity
import com.jimmy.avowsstore.data.local.entity.TransactionProductEntity

@Database(
    entities = [
        TransactionEntity::class,
        TransactionProductEntity::class
    ],
    version = 3,
)
abstract class BaseDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}