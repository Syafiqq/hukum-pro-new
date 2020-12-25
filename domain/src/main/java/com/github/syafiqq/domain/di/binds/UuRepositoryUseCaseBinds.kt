package com.github.syafiqq.domain.di.binds

import com.github.syafiqq.domain.usecase.uu.*
import dagger.Binds
import dagger.Module

@Module
abstract class UuRepositoryUseCaseBinds {
    @Binds
    abstract fun bindDeleteExistingUuRepositoryUseCase(concrete: DeleteExistingRepositoryUseCaseImpl): DeleteExistingUuRepositoryUseCase

    @Binds
    abstract fun bindUpdateUuRepositoryUseCase(concrete: UpdateUuRepositoryUseCaseImpl): UpdateUuRepositoryUseCase

    @Binds
    abstract fun bindUpdateUuOrderUseCase(concrete: UpdateUuOrderUseCaseImpl): UpdateUuOrderUseCase
}
