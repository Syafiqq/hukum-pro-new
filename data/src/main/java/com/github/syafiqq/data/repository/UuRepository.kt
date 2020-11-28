package com.github.syafiqq.data.repository

import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.entity.toData
import com.github.syafiqq.data.datasource.database.realm.entity.toData
import com.github.syafiqq.data.datasource.remote.firebase.contract.UuRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.toDomain
import com.github.syafiqq.domain.contract.repository.UuRepositoryInterface
import com.github.syafiqq.domain.entity.uu.UuEntity
import com.github.syafiqq.domain.entity.uu.UuOrderEntity
import com.github.syafiqq.domain.entity.uu.UuYearEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.github.syafiqq.data.datasource.remote.firebase.entity.UuEntity as DataUuEntity
import com.github.syafiqq.data.datasource.remote.firebase.entity.UuOrderEntity as DataUuOrderEntity

class UuRepository @Inject constructor(
    val uuRemoteDataSource: UuRemoteDataSource,
    val uuLocalDataSource: com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource,
    val uuCacheDataSource: UuCacheDataSource
) : UuRepositoryInterface {
    override suspend fun fetchRemoteUu(filename: String): List<UuEntity> {
        return withContext(Dispatchers.IO) {
            uuRemoteDataSource.fetchUu(filename)
                .map(DataUuEntity::toDomain)
        }
    }

    override suspend fun removeAllUu() {
        withContext(Dispatchers.IO) {
            uuLocalDataSource.removeAllUu()
            uuLocalDataSource.removeAllUuDocument()
        }
    }

    override suspend fun removeAllUuYear() {
        withContext(Dispatchers.IO) {
            uuLocalDataSource.removeAllUuYear()
        }
    }

    override suspend fun storeUu(uu: List<UuEntity>) {
        withContext(Dispatchers.IO) {
            val dataUu = uu.map(UuEntity::toData)
            uuLocalDataSource.storeUu(dataUu)
        }
    }

    override suspend fun storeUuYear(uuYear: List<UuYearEntity>) {
        withContext(Dispatchers.IO) {
            val dataUuYear = uuYear.map(UuYearEntity::toData)
            uuLocalDataSource.storeUuYear(dataUuYear)
        }
    }

    override suspend fun fetchRemoteUuOrder(): List<UuOrderEntity> {
        return withContext(Dispatchers.IO) {
            uuRemoteDataSource.fetchUuOrder()
                .map(DataUuOrderEntity::toDomain)
        }
    }

    override suspend fun storeUuOrder(uuOrder: List<UuOrderEntity>) {
        withContext(Dispatchers.IO) {
            uuCacheDataSource.storeUuOrder(uuOrder.map(UuOrderEntity::toData))
        }
    }
}
