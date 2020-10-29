package com.github.syafiqq.domain.di

import com.github.syafiqq.domain.contract.repository.AppProfileRepositoryInterface
import com.github.syafiqq.domain.contract.repository.UuRepositoryInterface

interface DomainInverseRepositoryDependency {
    fun provideDataVersionRepository(): AppProfileRepositoryInterface

    fun provideUuRepository(): UuRepositoryInterface
}
