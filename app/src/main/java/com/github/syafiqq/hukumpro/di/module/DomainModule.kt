package com.github.syafiqq.hukumpro.di.module

import com.github.syafiqq.domain.di.binds.RepositoryVersionUseCaseBinds
import com.github.syafiqq.domain.di.binds.UuRepositoryUseCaseBinds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        RepositoryVersionUseCaseBinds::class,
        UuRepositoryUseCaseBinds::class,
    ]
)
object DomainModule
