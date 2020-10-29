package com.github.syafiqq.data.di

import com.github.syafiqq.data.datasource.database.UuLocalDataSource
import com.github.syafiqq.data.datasource.database.realm.TruncatableRealmUuLocalDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RealDatabaseDataSourceBinds {
    @Binds
    abstract fun bindUuLocalDataSource(concrete: TruncatableRealmUuLocalDataSource): UuLocalDataSource
}
