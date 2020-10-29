package com.github.syafiqq.data.di

import com.github.syafiqq.data.datasource.cache.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.UuCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.TruncatableAppProfileSharedPreferenceDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.TruncatableUuSharedPreferenceDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RealCacheDataSourceBinds {
    @Binds
    abstract fun bindAppProfileCacheDataSource(concrete: TruncatableAppProfileSharedPreferenceDataSource): AppProfileCacheDataSource

    @Binds
    abstract fun bindUuCacheDataSource(concrete: TruncatableUuSharedPreferenceDataSource): UuCacheDataSource
}
