package com.jimmy.avowsstore.di

import com.jimmy.avowsstore.data.repository.UserRepositoryImpl
import com.jimmy.avowsstore.domain.repository.UserRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
}