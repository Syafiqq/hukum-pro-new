package com.github.syafiqq.data.repository

import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.entity.toData
import com.github.syafiqq.data.datasource.cache.sharedpref.entity.toDomain
import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.toDomain
import com.github.syafiqq.domain.contract.repository.AppProfileRepositoryInterface
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppProfileRepository @Inject constructor(
    val appProfileRemoteDataSource: AppProfileRemoteDataSource,
    val appProfileCacheDataSource: AppProfileCacheDataSource
) : AppProfileRepositoryInterface {
    override suspend fun fetchLocalVersion(): RepositoryVersionEntity? {
        return withContext(Dispatchers.IO) {
            appProfileCacheDataSource.fetchVersion()
                ?.toDomain()
        }
    }

    override suspend fun fetchRemoteVersion(): RepositoryVersionEntity {
        return withContext(Dispatchers.IO) {
            appProfileRemoteDataSource.fetchVersion().toDomain()
        }
    }

    override suspend fun updateVersion(version: RepositoryVersionEntity) {
        withContext(Dispatchers.IO) {
            appProfileCacheDataSource.storeVersion(version.toData())
        }
    }
}
