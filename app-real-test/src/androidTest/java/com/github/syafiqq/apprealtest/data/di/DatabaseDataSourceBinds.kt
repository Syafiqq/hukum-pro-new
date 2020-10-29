package com.github.syafiqq.apprealtest.data.di

import com.github.syafiqq.apprealtest.data.datasource.database.realm.TruncatableRealmUuLocalDataSource
import com.github.syafiqq.data.datasource.database.UuLocalDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DatabaseDataSourceBinds {
    @Binds
    abstract fun bindUuLocalDataSource(concrete: TruncatableRealmUuLocalDataSource): UuLocalDataSource
}
