package com.github.syafiqq.domain.di.binds

import com.github.syafiqq.domain.usecase.uu.DeleteExistingRepositoryUseCaseImpl
import com.github.syafiqq.domain.usecase.uu.DeleteExistingUuRepositoryUseCase
import com.github.syafiqq.domain.usecase.uu.UpdateUuRepositoryUseCase
import com.github.syafiqq.domain.usecase.uu.UpdateUuRepositoryUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UuRepositoryUseCaseBinds {
    @Binds
    abstract fun bindDeleteExistingUuRepositoryUseCase(concrete: DeleteExistingRepositoryUseCaseImpl): DeleteExistingUuRepositoryUseCase

    @Binds
    abstract fun bindUpdateUuRepositoryUseCase(concrete: UpdateUuRepositoryUseCaseImpl): UpdateUuRepositoryUseCase
}
