package com.github.syafiqq.realtestutil.data.di

import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import com.github.syafiqq.realtestutil.data.datasource.database.realm.TruncatableRealmUuLocalDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DatabaseDataSourceBinds {
    @Binds
    abstract fun bindUuLocalDataSource(concrete: TruncatableRealmUuLocalDataSource): UuLocalDataSource
}
