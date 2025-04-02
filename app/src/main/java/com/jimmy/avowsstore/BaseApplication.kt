package com.jimmy.avowsstore

import android.app.Application
import com.jimmy.avowsstore.di.appModule
import com.jimmy.avowsstore.di.databaseModule
import com.jimmy.avowsstore.di.networkModule
import com.jimmy.avowsstore.di.repositoryModule
import com.jimmy.avowsstore.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                appModule,
                databaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}