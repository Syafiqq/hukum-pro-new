package com.github.syafiqq.apprealtest.di.module

import android.content.Context
import com.github.syafiqq.apprealtest.domain.usecase.ClearAppUseCase
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object TestingModule {
    @Singleton
    @Provides
    fun provideClearAppUseCase(
        appContext: Context,
        baseUuLocalDataSource: UuLocalDataSource,
        baseUuCacheDataSource: UuCacheDataSource,
        baseAppProfileCacheDataSource: AppProfileCacheDataSource
    ): ClearAppUseCase {
        return ClearAppUseCase(
            appContext = appContext,
            baseUuLocalDataSource = baseUuLocalDataSource,
            baseUuCacheDataSource = baseUuCacheDataSource,
            baseAppProfileCacheDataSource = baseAppProfileCacheDataSource
        )
    }
}
