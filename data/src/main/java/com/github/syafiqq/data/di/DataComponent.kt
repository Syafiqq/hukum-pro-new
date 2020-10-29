package com.github.syafiqq.data.di

import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RealmModule::class,
        SharedPreferenceModule::class,
        RemoteDataSourceBinds::class,
        DatabaseDataSourceBinds::class,
        CacheDataSourceBinds::class,
    ]
)
@Singleton
interface DataComponent
