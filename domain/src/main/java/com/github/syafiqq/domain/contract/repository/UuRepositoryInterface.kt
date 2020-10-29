package com.github.syafiqq.domain.contract.repository

import com.github.syafiqq.domain.entity.uu.UuEntity
import com.github.syafiqq.domain.entity.uu.UuOrderEntity
import com.github.syafiqq.domain.entity.uu.UuYearEntity

interface UuRepositoryInterface {
    suspend fun fetchRemoteUu(filename: String): List<UuEntity>

    suspend fun removeAllUu()
    suspend fun removeAllUuYear()

    suspend fun storeUu(uu: List<UuEntity>)
    suspend fun storeUuYear(uuYear: List<UuYearEntity>)

    suspend fun fetchRemoteUuOrder(): List<UuOrderEntity>
    suspend fun storeUuOrder(uuOrder: List<UuOrderEntity>)
}

