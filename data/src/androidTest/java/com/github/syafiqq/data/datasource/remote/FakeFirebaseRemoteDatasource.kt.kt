package com.github.syafiqq.data.datasource.remote

import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity
import com.github.syafiqq.domain.entity.uu.UuEntity
import com.github.syafiqq.domain.entity.uu.UuOrderEntity
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

class FakeFirebaseRemoteDataSource @Inject constructor() : AppProfileRemoteDataSource,
    UuRemoteDataSource {
    override suspend fun fetchVersion(): RepositoryVersionEntity {
        delay(100)
        val milis = 1000000000000
        val date = Date(milis)
        return RepositoryVersionEntity(milis, date, filenames = emptyList())
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
