package com.github.syafiqq.data.datasource.cache

import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity

interface AppProfileCacheDataSource {
    suspend fun storeVersion(version: RepositoryVersionEntity)
    suspend fun fetchVersion(): RepositoryVersionEntity?
}
