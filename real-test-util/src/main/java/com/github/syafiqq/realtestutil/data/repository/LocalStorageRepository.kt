package com.github.syafiqq.realtestutil.data.repository

import com.github.syafiqq.common.contract.data.Truncatable
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import com.github.syafiqq.realtestutil.domain.contract.repository.LocalStorageRepositoryInterface
import javax.inject.Inject

class LocalStorageRepository @Inject constructor(
    var baseUuLocalDataSource: UuLocalDataSource,
    var baseUuCacheDataSource: UuCacheDataSource,
    var baseAppProfileCacheDataSource: AppProfileCacheDataSource,
) : LocalStorageRepositoryInterface {
    override suspend fun removeLocalDatabase() {
        (baseUuLocalDataSource as? Truncatable)?.truncate()
    }

    override suspend fun removeLocalCache() {
        (baseUuCacheDataSource as? Truncatable)?.truncate()
        (baseAppProfileCacheDataSource as? Truncatable)?.truncate()
    }

    override suspend fun removeAll() {
        removeLocalDatabase()
        removeLocalCache()
    }
}
