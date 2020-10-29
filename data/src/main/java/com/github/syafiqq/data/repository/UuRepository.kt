package com.github.syafiqq.data.repository

import com.github.syafiqq.data.datasource.cache.UuCacheDataSource
import com.github.syafiqq.data.datasource.database.UuLocalDataSource
import com.github.syafiqq.data.datasource.database.entity.toData
import com.github.syafiqq.data.datasource.remote.UuRemoteDataSource
import com.github.syafiqq.domain.contract.repository.UuRepositoryInterface
import com.github.syafiqq.domain.entity.uu.UuEntity
import com.github.syafiqq.domain.entity.uu.UuOrderEntity
import com.github.syafiqq.domain.entity.uu.UuYearEntity
import javax.inject.Inject

class UuRepository @Inject constructor(
    val uuRemoteDataSource: UuRemoteDataSource,
    val uuLocalDataSource: UuLocalDataSource,
    val uuCacheDataSource: UuCacheDataSource
) : UuRepositoryInterface {
    override suspend fun fetchRemoteUu(filename: String): List<UuEntity> {
        return uuRemoteDataSource.fetchUu(filename)
    }

    override suspend fun removeAllUu() {
        uuLocalDataSource.removeAllUu()
        uuLocalDataSource.removeAllUuDocument()
    }

    override suspend fun removeAllUuYear() {
        uuLocalDataSource.removeAllUuYear()
    }

    override suspend fun storeUu(uu: List<UuEntity>) {
        val dataUu = uu.map(UuEntity::toData)
        uuLocalDataSource.storeUu(dataUu)
    }

    override suspend fun storeUuYear(uuYear: List<UuYearEntity>) {
        val dataUuYear = uuYear.map(UuYearEntity::toData)
        uuLocalDataSource.storeUuYear(dataUuYear)
    }

    override suspend fun fetchRemoteUuOrder(): List<UuOrderEntity> {
        return uuRemoteDataSource.fetchUuOrder()
    }

    override suspend fun storeUuOrder(uuOrder: List<UuOrderEntity>) {
        uuCacheDataSource.storeUuOrder(uuOrder)
    }
}
