package com.jimmy.avowsstore.di

import com.jimmy.avowsstore.data.repository.AuthRepositoryImpl
import com.jimmy.avowsstore.data.repository.ProductRepositoryImpl
import com.jimmy.avowsstore.data.repository.UserRepositoryImpl
import com.jimmy.avowsstore.domain.repository.AuthRepository
import com.jimmy.avowsstore.domain.repository.ProductRepository
import com.jimmy.avowsstore.domain.repository.UserRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }
}