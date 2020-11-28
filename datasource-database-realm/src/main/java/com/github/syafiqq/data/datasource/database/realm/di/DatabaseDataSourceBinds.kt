package com.github.syafiqq.data.datasource.database.realm.di

import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import com.github.syafiqq.data.datasource.database.realm.implementation.RealmUuLocalDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DatabaseDataSourceBinds {
    @Binds
    abstract fun bindUuLocalDataSource(concrete: RealmUuLocalDataSource): UuLocalDataSource
}
