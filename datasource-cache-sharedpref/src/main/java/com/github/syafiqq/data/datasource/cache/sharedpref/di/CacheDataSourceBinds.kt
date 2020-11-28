package com.github.syafiqq.data.datasource.cache.sharedpref.di

import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.implementation.AppProfileSharedPreferenceDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.implementation.UuSharedPreferenceDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class CacheDataSourceBinds {
    @Binds
    abstract fun bindAppProfileCacheDataSource(concrete: AppProfileSharedPreferenceDataSource): AppProfileCacheDataSource

    @Binds
    abstract fun bindUuCacheDataSource(concrete: UuSharedPreferenceDataSource): UuCacheDataSource
}
