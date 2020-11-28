package com.github.syafiqq.data.datasource.remote.firebase.contract

import com.github.syafiqq.data.datasource.remote.firebase.entity.UuEntity
import com.github.syafiqq.data.datasource.remote.firebase.entity.UuOrderEntity

interface UuRemoteDataSource {
    suspend fun fetchUuOrder(): List<UuOrderEntity>
    suspend fun fetchUu(filename: String): List<UuEntity>
}
