package com.github.syafiqq.data.datasource.database.realm.contract

import com.github.syafiqq.data.datasource.database.realm.entity.UuDocumentEntity
import com.github.syafiqq.data.datasource.database.realm.entity.UuEntity
import com.github.syafiqq.data.datasource.database.realm.entity.UuYearEntity

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
