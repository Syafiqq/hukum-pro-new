package com.github.syafiqq.realtestutil.di

import com.github.syafiqq.realtestutil.data.repository.LocalStorageRepository
import com.github.syafiqq.realtestutil.domain.contract.repository.LocalStorageRepositoryInterface
import com.github.syafiqq.realtestutil.domain.usecase.ClearLocalDataUseCase
import com.github.syafiqq.realtestutil.domain.usecase.ClearLocalDataUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ClearLocalDataBinds {
    @Binds
    abstract fun bindClearLocalUseCase(concrete: LocalStorageRepository): LocalStorageRepositoryInterface

    @Binds
    abstract fun bindClearLocalUseCase(concrete: ClearLocalDataUseCaseImpl): ClearLocalDataUseCase
}
