package com.github.syafiqq.hukumpro.di.module

import com.github.syafiqq.data.di.DomainRepositoryBinds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        DomainRepositoryBinds::class,
    ]
)
object DomainDataModule
