package com.github.syafiqq.data.repository

import com.github.syafiqq.data.datasource.cache.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.remote.AppProfileRemoteDataSource
import com.github.syafiqq.domain.contract.repository.AppProfileRepositoryInterface
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity
import javax.inject.Inject

class AppProfileRepository @Inject constructor(
    val appProfileRemoteDataSource: AppProfileRemoteDataSource,
    val appProfileCacheDataSource: AppProfileCacheDataSource
) : AppProfileRepositoryInterface {
    override suspend fun fetchLocalVersion(): RepositoryVersionEntity? {
        return appProfileCacheDataSource.fetchVersion()
    }

    override suspend fun fetchRemoteVersion(): RepositoryVersionEntity {
        return appProfileRemoteDataSource.fetchVersion()
    }

    override suspend fun updateVersion(version: RepositoryVersionEntity) {
        appProfileCacheDataSource.storeVersion(version)
    }
}
