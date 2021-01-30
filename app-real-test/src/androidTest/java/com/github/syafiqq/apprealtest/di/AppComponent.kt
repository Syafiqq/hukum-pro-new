package com.github.syafiqq.apprealtest.di

import android.content.Context
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
import com.github.syafiqq.data.datasource.remote.firebase.di.RemoteDataSourceBinds
import com.github.syafiqq.data.di.DomainRepositoryBinds
import com.github.syafiqq.domain.di.binds.RepositoryVersionUseCaseBinds
import com.github.syafiqq.domain.di.binds.UuRepositoryUseCaseBinds
import com.github.syafiqq.realtestutil.di.CacheDataSourceBinds
import com.github.syafiqq.realtestutil.di.ClearLocalDataBinds
import com.github.syafiqq.realtestutil.di.DatabaseDataSourceBinds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppContextModule {
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context {
        return context
    }
}

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        RealmModule::class,
        SharedPreferenceModule::class,
        RemoteDataSourceBinds::class,
        DatabaseDataSourceBinds::class,
        CacheDataSourceBinds::class,
    ]
)
object DataLayerModule

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        DomainRepositoryBinds::class,
    ]
)
object DataDomainLayerInversionModule

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        RepositoryVersionUseCaseBinds::class,
        UuRepositoryUseCaseBinds::class,
    ]
)
object DomainLayerModule

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        ClearLocalDataBinds::class,
    ]
)
object TestingModule
