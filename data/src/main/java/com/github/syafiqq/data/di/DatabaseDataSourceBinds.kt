package com.github.syafiqq.data.di

import com.github.syafiqq.data.datasource.database.UuLocalDataSource
import com.github.syafiqq.data.datasource.database.realm.RealmUuLocalDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DatabaseDataSourceBinds {
    @Binds
    abstract fun bindUuLocalDataSource(concrete: RealmUuLocalDataSource): UuLocalDataSource
}
