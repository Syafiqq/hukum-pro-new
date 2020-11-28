package com.github.syafiqq.realtestutil.data.di

import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.realtestutil.data.datasource.cache.sharedpref.TruncatableAppProfileSharedPreferenceDataSource
import com.github.syafiqq.realtestutil.data.datasource.cache.sharedpref.TruncatableUuSharedPreferenceDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class CacheDataSourceBinds {
    @Binds
    abstract fun bindAppProfileCacheDataSource(concrete: TruncatableAppProfileSharedPreferenceDataSource): AppProfileCacheDataSource

    @Binds
    abstract fun bindUuCacheDataSource(concrete: TruncatableUuSharedPreferenceDataSource): UuCacheDataSource
}
