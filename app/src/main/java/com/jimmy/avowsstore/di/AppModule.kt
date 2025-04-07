package com.jimmy.avowsstore.di

import com.jimmy.avowsstore.data.local.SharedPreferenceManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::SharedPreferenceManager)
}