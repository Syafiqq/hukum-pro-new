package com.github.syafiqq.hukumpro.di.module

import com.github.syafiqq.domain.di.binds.RepositoryVersionUseCaseBinds
import com.github.syafiqq.domain.di.binds.UuRepositoryUseCaseBinds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module(
    includes = [
        RepositoryVersionUseCaseBinds::class,
        UuRepositoryUseCaseBinds::class,
    ]
)
object DomainModule
