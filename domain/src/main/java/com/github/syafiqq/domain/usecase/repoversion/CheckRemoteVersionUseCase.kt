package com.github.syafiqq.domain.usecase.repoversion

import com.github.syafiqq.domain.contract.repository.AppProfileRepositoryInterface
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionState
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpToDate
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateAvailable
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateRequired
import javax.inject.Inject

interface CheckRemoteVersionUseCase {
    suspend fun execute(): RepositoryVersionState
}

class CheckRemoteVersionUseCaseImpl @Inject constructor(
    val versionRepository: AppProfileRepositoryInterface
) : CheckRemoteVersionUseCase {
    override suspend fun execute(): RepositoryVersionState {
        val localVersion = versionRepository.fetchLocalVersion()
        val remoteVersion = versionRepository.fetchRemoteVersion()
        val localVersionMilis = localVersion?.milis ?: -1
        val remoteVersionMilis = remoteVersion.milis ?: throw InvalidRepositoryVersionException
        return when {
            localVersion == null -> RepositoryVersionUpdateRequired(to = remoteVersion)
            localVersionMilis < remoteVersionMilis -> RepositoryVersionUpdateAvailable(to = remoteVersion)
            else -> RepositoryVersionUpToDate
        }
    }
}

object InvalidRepositoryVersionException : Throwable("Invalid repository version")
