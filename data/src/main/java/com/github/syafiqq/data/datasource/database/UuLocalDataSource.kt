package com.github.syafiqq.data.datasource.database

import com.github.syafiqq.data.datasource.database.entity.UuDocumentEntity
import com.github.syafiqq.data.datasource.database.entity.UuEntity
import com.github.syafiqq.data.datasource.database.entity.UuYearEntity

interface UuLocalDataSource {
    suspend fun storeUu(uu: List<UuEntity>)
    suspend fun storeUuYear(entity: List<UuYearEntity>)
    suspend fun storeUuDocument(document: List<UuDocumentEntity>)
    suspend fun updateUuDocument(id: String, document: String)
    suspend fun fetchUu(id: String): UuEntity
    suspend fun fetchUuByCategoryAndYear(category: Int, year: Int): List<UuEntity>
    suspend fun fetchUuDocument(id: String): UuDocumentEntity
    suspend fun fetchUuYear(category: Int): List<UuYearEntity>
    suspend fun removeAllUu()
    suspend fun removeAllUuDocument()
    suspend fun removeAllUuYear()
}
