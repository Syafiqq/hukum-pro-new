package com.github.syafiqq.data.di

import com.github.syafiqq.data.datasource.cache.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.UuCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.AppProfileSharedPreferenceDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.UuSharedPreferenceDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class CacheDataSourceBinds {
    @Binds
    abstract fun bindAppProfileCacheDataSource(concrete: AppProfileSharedPreferenceDataSource): AppProfileCacheDataSource

    @Binds
    abstract fun bindUuCacheDataSource(concrete: UuSharedPreferenceDataSource): UuCacheDataSource
}
