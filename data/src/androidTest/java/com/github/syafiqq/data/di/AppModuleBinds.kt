package com.github.syafiqq.data.di

import com.github.syafiqq.data.datasource.remote.FakeFirebaseRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.contract.UuRemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class AppModuleBinds {
    @Binds
    abstract fun bindAppProfileRemoteDataSource(concrete: FakeFirebaseRemoteDataSource): AppProfileRemoteDataSource

    @Binds
    abstract fun bindUuRemoteDataSource(concrete: FakeFirebaseRemoteDataSource): UuRemoteDataSource
}
