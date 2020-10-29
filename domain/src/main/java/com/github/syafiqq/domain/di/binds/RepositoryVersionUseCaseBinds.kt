package com.github.syafiqq.domain.di.binds

import com.github.syafiqq.domain.usecase.repoversion.*
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryVersionUseCaseBinds {
    @Binds
    abstract fun loadLocalVersionUseCase(concrete: CheckLocalVersionUseCaseImpl): CheckLocalVersionUseCase

    @Binds
    abstract fun loadRemoteVersionUseCase(concrete: CheckRemoteVersionUseCaseImpl): CheckRemoteVersionUseCase

    @Binds
    abstract fun bindUpdateVersionUseCase(concrete: UpdateRepositoryVersionUseCaseImpl): UpdateRepositoryVersionUseCase
}
