package com.jimmy.avowsstore.di

import android.content.Context
import androidx.room.Room
import com.jimmy.avowsstore.data.local.database.BaseDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::provideDatabase)
    singleOf(::provideTransactionDao)
}

fun provideDatabase(context: Context) = Room.databaseBuilder(
    context,
    BaseDatabase::class.java,
    "base.db"
).fallbackToDestructiveMigration().build()

fun provideTransactionDao(database: BaseDatabase) = database.transactionDao()