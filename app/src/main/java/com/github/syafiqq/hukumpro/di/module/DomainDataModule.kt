package com.github.syafiqq.hukumpro.di.module

import com.github.syafiqq.data.di.DomainRepositoryBinds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module(
    includes = [
        DomainRepositoryBinds::class,
    ]
)
object DomainDataModule
