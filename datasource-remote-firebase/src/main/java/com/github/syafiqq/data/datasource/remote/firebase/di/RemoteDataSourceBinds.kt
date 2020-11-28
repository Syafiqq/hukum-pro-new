package com.github.syafiqq.data.datasource.remote.firebase.di

import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.contract.UuRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.implementation.FirebaseRemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteDataSourceBinds {
    @Binds
    abstract fun bindAppProfileRemoteDataSource(concrete: FirebaseRemoteDataSource): AppProfileRemoteDataSource

    @Binds
    abstract fun bindUuRemoteDataSource(concrete: FirebaseRemoteDataSource): UuRemoteDataSource
}
