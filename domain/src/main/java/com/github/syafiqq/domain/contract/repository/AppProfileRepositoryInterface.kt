package com.github.syafiqq.domain.contract.repository

import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity

interface AppProfileRepositoryInterface {
    suspend fun fetchLocalVersion(): RepositoryVersionEntity?
    suspend fun fetchRemoteVersion(): RepositoryVersionEntity
    suspend fun updateVersion(version: RepositoryVersionEntity)
}
