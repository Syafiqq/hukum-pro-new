package com.github.syafiqq.hukumpro.di.module

import com.github.syafiqq.data.datasource.cache.sharedpref.di.CacheDataSourceBinds
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.database.realm.di.DatabaseDataSourceBinds
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
import com.github.syafiqq.data.datasource.remote.firebase.di.RemoteDataSourceBinds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module(
    includes = [
        RealmModule::class,
        SharedPreferenceModule::class,
        RemoteDataSourceBinds::class,
        DatabaseDataSourceBinds::class,
        CacheDataSourceBinds::class,
    ]
)
object DataModule
