package com.github.syafiqq.data.di

import com.github.syafiqq.data.repository.AppProfileRepository
import com.github.syafiqq.data.repository.UuRepository
import com.github.syafiqq.domain.contract.repository.AppProfileRepositoryInterface
import com.github.syafiqq.domain.contract.repository.UuRepositoryInterface
import dagger.Binds
import dagger.Module

@Module
abstract class DomainRepositoryBinds {
    @Binds
    abstract fun bindAppProfileRepository(concrete: AppProfileRepository): AppProfileRepositoryInterface

    @Binds
    abstract fun bindUuRepository(concrete: UuRepository): UuRepositoryInterface
}
