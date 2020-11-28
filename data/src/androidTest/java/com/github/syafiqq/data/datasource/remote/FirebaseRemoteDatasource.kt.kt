package com.github.syafiqq.data.datasource.remote

import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.contract.UuRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.entity.RepositoryVersionDetailEntity
import com.github.syafiqq.data.datasource.remote.firebase.entity.RepositoryVersionEntity
import com.github.syafiqq.data.datasource.remote.firebase.entity.UuEntity
import com.github.syafiqq.data.datasource.remote.firebase.entity.UuOrderEntity
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeFirebaseRemoteDataSource @Inject constructor() : AppProfileRemoteDataSource,
    UuRemoteDataSource {
    override suspend fun fetchVersion(): RepositoryVersionEntity {
        delay(100)
        val milis = 1000000000000
        val date = "10-10-2010 10:10:10"
        return RepositoryVersionEntity(
            milis = milis,
            timestamp = date,
            detail = RepositoryVersionDetailEntity(filenames = emptyList())
        )
    }

    override suspend fun fetchUuOrder(): List<UuOrderEntity> {
        delay(100)
        return emptyList()
    }

    override suspend fun fetchUu(filename: String): List<UuEntity> {
        delay(100)
        return emptyList()
    }
}
