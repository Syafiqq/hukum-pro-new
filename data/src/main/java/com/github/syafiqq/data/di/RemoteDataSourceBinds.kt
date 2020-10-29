package com.github.syafiqq.data.di

import com.github.syafiqq.data.datasource.remote.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.UuRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.FirebaseRemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteDataSourceBinds {
    @Binds
    abstract fun bindAppProfileRemoteDataSource(concrete: FirebaseRemoteDataSource): AppProfileRemoteDataSource

    @Binds
    abstract fun bindUuRemoteDataSource(concrete: FirebaseRemoteDataSource): UuRemoteDataSource
}
