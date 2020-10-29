package com.github.syafiqq.domain.usecase.repoversion

import com.github.syafiqq.domain.contract.repository.AppProfileRepositoryInterface
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionState
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpToDate
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateRequired
import javax.inject.Inject

interface CheckLocalVersionUseCase {
    suspend fun execute(): RepositoryVersionState
}

class CheckLocalVersionUseCaseImpl @Inject constructor(
    val versionRepository: AppProfileRepositoryInterface
) : CheckLocalVersionUseCase {
    override suspend fun execute(): RepositoryVersionState {
        val localVersion = versionRepository.fetchLocalVersion()
        return if (localVersion == null) {
            val remoteVersion = versionRepository.fetchRemoteVersion()
            RepositoryVersionUpdateRequired(to = remoteVersion)
        } else {
            RepositoryVersionUpToDate
        }
    }
}
