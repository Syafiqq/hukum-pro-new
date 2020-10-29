package com.github.syafiqq.apprealtest.data.di

import com.github.syafiqq.apprealtest.data.datasource.cache.sharedpref.TruncatableAppProfileSharedPreferenceDataSource
import com.github.syafiqq.apprealtest.data.datasource.cache.sharedpref.TruncatableUuSharedPreferenceDataSource
import com.github.syafiqq.data.datasource.cache.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.UuCacheDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class CacheDataSourceBinds {
    @Binds
    abstract fun bindAppProfileCacheDataSource(concrete: TruncatableAppProfileSharedPreferenceDataSource): AppProfileCacheDataSource

    @Binds
    abstract fun bindUuCacheDataSource(concrete: TruncatableUuSharedPreferenceDataSource): UuCacheDataSource
}
