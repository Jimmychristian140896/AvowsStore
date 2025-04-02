package com.jimmy.avowsstore.di

import com.jimmy.avowsstore.core.network.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::provideHttpClientEngine)
    singleOf(::provideHttpClient)
}

fun provideHttpClientEngine() = CIO.create()

fun provideHttpClient(engine: HttpClientEngine) = HttpClientFactory.create(engine)