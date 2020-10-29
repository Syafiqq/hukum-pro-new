package com.github.syafiqq.domain.usecase.repoversion

import com.github.syafiqq.domain.contract.repository.AppProfileRepositoryInterface
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity
import javax.inject.Inject

interface UpdateRepositoryVersionUseCase {
    suspend fun execute(to: RepositoryVersionEntity)
}

class UpdateRepositoryVersionUseCaseImpl @Inject constructor(
    val versionRepository: AppProfileRepositoryInterface
) : UpdateRepositoryVersionUseCase {
    override suspend fun execute(to: RepositoryVersionEntity) {
        versionRepository.updateVersion(to)
    }
}
