package com.github.syafiqq.domain.di

import com.github.syafiqq.domain.di.binds.RepositoryVersionUseCaseBinds
import com.github.syafiqq.domain.di.binds.UuRepositoryUseCaseBinds
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RepositoryVersionUseCaseBinds::class,
        UuRepositoryUseCaseBinds::class,
    ]
)
@Singleton
interface DomainComponent
