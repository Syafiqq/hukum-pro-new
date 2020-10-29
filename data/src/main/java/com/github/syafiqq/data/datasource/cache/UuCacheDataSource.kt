package com.github.syafiqq.data.datasource.cache

import com.github.syafiqq.domain.entity.uu.UuDocumentDownloadEntity
import com.github.syafiqq.domain.entity.uu.UuOrderEntity

interface UuCacheDataSource {
    suspend fun storeUuOrder(orders: List<UuOrderEntity>)
    suspend fun fetchUuOrder(): List<UuOrderEntity>
    suspend fun storeDocumentDownloadTracker(tracker: List<UuDocumentDownloadEntity>)
    suspend fun fetchDocumentDownloadTracker(): List<UuDocumentDownloadEntity>
}
