package com.github.syafiqq.data.datasource.cache.sharedpref.contract

import com.github.syafiqq.data.datasource.cache.sharedpref.entity.RepositoryVersionEntity

interface AppProfileCacheDataSource {
    suspend fun storeVersion(version: RepositoryVersionEntity)
    suspend fun fetchVersion(): RepositoryVersionEntity?
}
