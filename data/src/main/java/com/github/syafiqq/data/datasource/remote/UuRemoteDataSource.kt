package com.github.syafiqq.data.datasource.remote

import com.github.syafiqq.domain.entity.uu.UuEntity
import com.github.syafiqq.domain.entity.uu.UuOrderEntity

interface UuRemoteDataSource {
    suspend fun fetchUuOrder(): List<UuOrderEntity>
    suspend fun fetchUu(filename: String): List<UuEntity>
}
